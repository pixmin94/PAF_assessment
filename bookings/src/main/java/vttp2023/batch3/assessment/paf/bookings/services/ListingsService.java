package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.Utils;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;
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


	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
