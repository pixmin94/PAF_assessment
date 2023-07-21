package vttp2023.batch3.assessment.paf.bookings;

import java.sql.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@SpringBootApplication
public class BookingsApplication {//implements CommandLineRunner {

	// @Autowired
	// private ListingsRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(BookingsApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	// List<String> result = repo.getAllCountries();

	// 	// Search s = new Search();
	// 	// s.setCountry("Australia");
	// 	// s.setPax(2);
	// 	// s.setMin(1);
	// 	// s.setMax(100);
	// 	// List<Document> result = repo.getSearchListings(s);

	// 	// Document result = repo.getAccomsDetail("16134812");

	// 	// Integer result = repo.getVacancy(95560);
	// 	// Integer result = repo.updateVacancy(95560, 3);

	// 	// String resvId, Integer accId, String custName,
	// 	// String email, Date arrival, Integer duration
	// 	Booking b = new Booking(95560, "fred", "fred@fred.com", new java.sql.Date(System.currentTimeMillis()) , 3);
	// 	Integer result = repo.createBooking(b);

	// 	System.out.println(">>>>>>>>>>> \n");
	// 	System.out.println(result);

	// 	// for (Document c : result) {
	// 	// 	System.out.println(c);
	// 	// }
	// 	System.exit(0);
	//  }

}
