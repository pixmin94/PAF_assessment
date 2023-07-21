package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;
import vttp2023.batch3.assessment.paf.bookings.models.Search;
import vttp2023.batch3.assessment.paf.bookings.models.Accoms;

@Controller
@RequestMapping
public class ListingsController {
	@Autowired
	private ListingsService service;

	@GetMapping(path="/")
	public String getSearchForm(Model m) {
		List<String> countries = service.getAllCountries();
		m.addAttribute("countries", countries);
		return "index";
	}
	
	@PostMapping(path="/search")
	public ModelAndView getSearchListing(@ModelAttribute Search search) {
		// System.out.println(search);
		List<Accoms> listing = service.getSearchListings(search);
		System.out.println(listing.get(0));

		ModelAndView mav = new ModelAndView();
		mav.setViewName("listings");
        mav.addObject("accoms", listing);
		mav.addObject("country", search.getCountry());
        mav.setStatus(HttpStatusCode.valueOf(200));

		return mav;
	}

	@PostMapping(path="/booking")
	public String getAccomDetails(@RequestBody String accomId, Model m) {
		System.out.println(accomId);
		m.addAttribute("message", accomId);
	
		return "test";
	}

	//TODO: Task 4
	

	//TODO: Task 5


}
