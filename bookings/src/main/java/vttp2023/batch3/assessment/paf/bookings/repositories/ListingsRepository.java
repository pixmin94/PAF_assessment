package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;

@Repository
public class ListingsRepository {
	public static final String F_COUNTRY = "address.country";
	public static final String C_LISTINGS = "listings";

	public static final String check_vacancy_SQL = "select vacancy from acc_occupancy where acc_id = ?";
	public static final String update_vacancy_SQL = "update acc_occupancy set vacancy = vacancy - ? where acc_id = ?";
	public static final String create_booking_SQL = "insert into reservations (cust_name, email, acc_id, arrival_date, duration) values (?,?,?,?,?)";


	@Autowired
	private MongoTemplate mgtemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// db.listings.distinct("address.country")
	public List<String> getAllCountries() {
		List<String> countries = mgtemplate.findDistinct(new Query(), F_COUNTRY, C_LISTINGS, String.class);

		return countries;
	}
	
	/* db.listings.aggregate([
		{ $match: {
			$and: [
				{ "address.country" : "Australia"}, 
				{ "accommodates" : {$gte: 2} },
				{ "price" : {$gte: 1, $lte: 100} }
			]
		} },
		{ $sort: {price: -1} },
		{ $project: {_id: 0, name: 1, "images.picture_url": 1, price: 1}}
	]) */
	public List<Document> getSearchListings(Search search) {
		Criteria c = new Criteria().andOperator(
			Criteria.where("address.country").is(search.getCountry()),
			Criteria.where("accommodates").gte(search.getPax()),
			Criteria.where("price").gte(search.getMin()).lte(search.getMax())
			);
		MatchOperation match = Aggregation.match(c);

		SortOperation sort = Aggregation.sort(Sort.by(Direction.DESC, "price"));

		ProjectionOperation project = Aggregation.project(
			"address.street", "images.picture_url", "price")
			;

		Aggregation pipeline = Aggregation.newAggregation(match, sort, project);

		AggregationResults<Document> result = mgtemplate.aggregate(pipeline, C_LISTINGS, Document.class);
		
		return result.getMappedResults();
	}

	/* db.listings.aggregate(
		{ $match: { _id: "16231922" } },
		{ $project: { description: 1, "address.street": 1, "address.country": 1, 
			"address.suburb": 1, "images.picture_url": 1, price: 1, amenities: 1}}
	) */
	public Document getAccomsDetail(String accomId) {
		MatchOperation match = Aggregation.match(Criteria.where("_id").is(accomId));
		ProjectionOperation project = Aggregation.project(
			"description", "address.street", "address.suburb", 
			"address.country", "images.picture_url", "price", "amenities");
		Aggregation pipeline = Aggregation.newAggregation(match, project);

		AggregationResults<Document> result = mgtemplate.aggregate(pipeline, C_LISTINGS, Document.class);

		return result.getUniqueMappedResult();
	}
	

	public Integer getVacancy(Integer accomsId) {
		return (Integer) jdbcTemplate.queryForObject(check_vacancy_SQL, Integer.class, accomsId);
	}

	public Integer updateVacancy(Integer accomsId, Integer stay) {
		int iUpdated = 0;

        iUpdated = jdbcTemplate.update(update_vacancy_SQL, stay, accomsId);

		return iUpdated;
	}

	public Integer createBooking(Booking booking) {
        KeyHolder generatedKey = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(create_booking_SQL, new String[] { "id" });
                // (resv_id, cust_name, email, acc_id, arrival_date, duration)
				ps.setString(1, booking.custName());
				ps.setString(2, booking.email());
				ps.setInt(3, booking.accId());
				ps.setDate(4, booking.arrival());
				ps.setInt(5, booking.duration());
                
                return ps;
            }

        };

        jdbcTemplate.update(psc, generatedKey);

        Integer createdBookingId = generatedKey.getKey().intValue();
        return createdBookingId;
    }



}
