package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;

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
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
