package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.Utils;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
import vttp2023.batch3.assessment.paf.bookings.models.Details;
import vttp2023.batch3.assessment.paf.bookings.models.Booking;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {
	@Autowired
	private ListingsRepository repo;
	
	public List<String> getAllCountries() {
		List<String> countries = repo.getAllCountries();
		return countries;
	}

	public List<Accoms> getSearchListings(Search search) {
		List<Document> listings = repo.getSearchListings(search);
		List<Accoms> accomsListing = listings.stream()
			.map(o -> Utils.toAccomsObject(o))
			.collect(Collectors.toList());
	
		return accomsListing;
	}

	public Details getAccomDetails(String accomId) {
		Details deets = Utils.toDetailsObject(repo.getAccomsDetail(accomId));
		return deets;
	}

	@Transactional
	public Integer createBooking(Booking booking) {
		Integer vacancy = repo.getVacancy(booking.accId());

		if ( booking.duration() <= vacancy) {
			repo.updateVacancy(booking.accId(), booking.duration());
		} else {
			throw new IllegalArgumentException("Not enough vacancy!");
		}

		Integer resvId = repo.createBooking(booking);
		System.out.printf("From service: %d" , resvId);
		return resvId;
	}


}
