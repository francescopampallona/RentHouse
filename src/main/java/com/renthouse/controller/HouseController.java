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
		
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth, this.userService.getCurrentUser());
		houseService.insert(house);
		
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
		
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice, lowSeasonStartMonth, mediumSeasonPrice, mediumSeasonStartMonth, highSeasonPrice, highSeasonStartMonth,this.userService.getCurrentUser());
		house.setId(id);
		houseService.update(house);
		
		this.setHouses(request);
		return "houseManagement";
	}
	
	//SET FOR UPDATE OF THE VIEW
	private void setHouses(HttpServletRequest request) {
		Iterable<House> houses = houseService.findAll();
		request.setAttribute("houses", houses);
		
	}
	
	

}
