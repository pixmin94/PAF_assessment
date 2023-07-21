package vttp2023.batch3.assessment.paf.bookings.repositories;

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

import vttp2023.batch3.assessment.paf.bookings.models.Search;

@Repository
public class ListingsRepository {
	public static final String F_COUNTRY = "address.country";
	public static final String C_LISTINGS = "listings";

	@Autowired
	private MongoTemplate template;

	// db.listings.distinct("address.country")
	public List<String> getAllCountries() {
		List<String> countries = template.findDistinct(new Query(), F_COUNTRY, C_LISTINGS, String.class);

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

		AggregationResults<Document> result = template.aggregate(pipeline, C_LISTINGS, Document.class);
		
		return result.getMappedResults();
	}


	//TODO: Task 4
	

	//TODO: Task 5


}
