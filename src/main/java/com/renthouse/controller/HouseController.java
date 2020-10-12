package com.renthouse.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.House;
import com.renthouse.service.HouseService;
import com.renthouse.service.UserService;

@Controller
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public String getAll(HttpServletRequest request) {
		this.setHouses(request);
		return "houseManagement";
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, 
			@RequestParam("nation") String nation,
			@RequestParam("city") String city,
			@RequestParam("address") String address,
			@RequestParam("civicNumber") int civicNumber,
			@RequestParam("maxGuests") int maxGuests,
			@RequestParam("lowSeasonPrice") float lowSeasonPrice,
			@RequestParam("lowSeasonStartMonth") String lowSeasonStartMonth,
			@RequestParam("mediumSeasonPrice") float mediumSeasonPrice,
			@RequestParam("mediumSeasonStartMonth") String mediumSeasonStartMonth,
			@RequestParam("highSeasonPrice") float highSeasonPrice,
			@RequestParam("highSeasonStartMonth") String highSeasonStartMonth) {
		
		if(dataIsValid(request, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth)) {
		request.setAttribute("successMessage", "House created successfully");
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth, this.userService.getCurrentUser());
		houseService.insert(house);
		}
		
		
		this.setHouses(request);
		return "houseManagement";
	}
	
	@PostMapping("/update/{id}")
	public String update(HttpServletRequest request, 
			@PathVariable("id") long id,
			@RequestParam("nation") String nation,
			@RequestParam("city") String city,
			@RequestParam("address") String address,
			@RequestParam("civicNumber") int civicNumber,
			@RequestParam("maxGuests") int maxGuests,
			@RequestParam("lowSeasonPrice") float lowSeasonPrice,
			@RequestParam("lowSeasonStartMonth") String lowSeasonStartMonth,
			@RequestParam("mediumSeasonPrice") float mediumSeasonPrice,
			@RequestParam("mediumSeasonStartMonth") String mediumSeasonStartMonth,
			@RequestParam("highSeasonPrice") float highSeasonPrice,
			@RequestParam("highSeasonStartMonth") String highSeasonStartMonth) {
		
		
		if(dataIsValid(request, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth)) {
		request.setAttribute("successMessage", "House updated successfully");
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth,this.userService.getCurrentUser());
		house.setId(id);
		houseService.update(house);
		}
		
		this.setHouses(request);
		return "houseManagement";
	}
	
	//SET FOR UPDATE OF THE VIEW
	private void setHouses(HttpServletRequest request) {
		Iterable<House> houses = houseService.findAll();
		request.setAttribute("houses", houses);
		
	}
	
	//Check validity of data inserted by user
	private boolean dataIsValid(HttpServletRequest request, float lowSeasonPrice, String lowSeasonStartMonth, float mediumSeasonPrice, String mediumSeasonStartMonth, float highSeasonPrice, String highSeasonStartMonth) {
		if((lowSeasonPrice>mediumSeasonPrice) || (lowSeasonPrice>highSeasonPrice) || (mediumSeasonPrice>highSeasonPrice)) {
			if(lowSeasonPrice>mediumSeasonPrice) {
				request.setAttribute("errorMessage", "Low season price should be less than medium season price");
			}
			else if(lowSeasonPrice>=highSeasonPrice) {
				request.setAttribute("errorMessage", "Low season price should be less than high season price");
			}
			else {
				request.setAttribute("errorMessage", "Medium season price should be less than high season price");
			}
			return false;
		}
		if((lowSeasonStartMonth.equals(mediumSeasonStartMonth)) || (lowSeasonStartMonth.equals(highSeasonStartMonth)) || (mediumSeasonStartMonth.equals(highSeasonStartMonth))) {
			if(lowSeasonStartMonth.equals(mediumSeasonStartMonth)) {
				request.setAttribute("errorMessage", "Low season start month should be different from medium season start month");
			}
			else if(lowSeasonStartMonth.equals(highSeasonStartMonth)) {
				request.setAttribute("errorMessage", "Low season start month should be different from high season start month");
			}
			else {
				request.setAttribute("errorMessage", "Medium season start month should be different from high season start month");
			}
			return false;
		}
		
		return true;
	}
	
	

}
