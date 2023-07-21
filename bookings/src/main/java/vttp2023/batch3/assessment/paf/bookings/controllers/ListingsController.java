package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

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
	
	//TODO: Task 3


	//TODO: Task 4
	

	//TODO: Task 5


}
