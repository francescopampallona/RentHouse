package com.renthouse.controller;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;
import com.renthouse.service.HouseService;
import com.renthouse.service.RentAnnouncementService;
import com.renthouse.service.UserService;

@Controller
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RentAnnouncementService rentAnnouncementService;

	
	@GetMapping
	public String getAll(HttpServletRequest request) {
		this.setHouses(request);
		return "houseManagement";
	}
	
	@GetMapping("/search")
	public String searchHouse(HttpServletRequest request, @RequestParam Optional<String> nation,
			@RequestParam Optional<String> city, @RequestParam Optional<Integer> maxNumberOfGuests, @RequestParam Optional<String> address, @RequestParam Optional<Integer> civicNumber) {
		
		Iterable<House> houses = this.houseService.searchByParams(nation.get(), city.get(), maxNumberOfGuests, address.get(), civicNumber);
		request.setAttribute("houses", houses);
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
			@RequestParam("mediumSeasonPrice") float mediumSeasonPrice,
			@RequestParam("highSeasonPrice") float highSeasonPrice) {
		
		if(dataIsValid(request, lowSeasonPrice, mediumSeasonPrice,highSeasonPrice)) {
		request.setAttribute("successMessage", "House created successfully");
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice,  mediumSeasonPrice,  highSeasonPrice,  this.userService.getCurrentUser());
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
			@RequestParam("mediumSeasonPrice") float mediumSeasonPrice, 
			@RequestParam("highSeasonPrice") float highSeasonPrice) {
		
		
		if(dataIsValid(request, lowSeasonPrice, mediumSeasonPrice,  highSeasonPrice)) {
		request.setAttribute("successMessage", "House updated successfully");
		House house = new House(nation, city, address, civicNumber, maxGuests, lowSeasonPrice, mediumSeasonPrice,  highSeasonPrice, this.userService.getCurrentUser());
		house.setId(id);
		houseService.update(house);
		}
		
		this.setHouses(request);
		return "houseManagement";
	}
	
	@PostMapping("/{house_id}/rentAnnouncement/insert")
	public String insertRentAnnouncement(HttpServletRequest request,
			@PathVariable("house_id") long house_id,
			@RequestParam("description") String description,
			@RequestParam("daysForDiscountValidity") int daysForDiscountValidity,
			@RequestParam("discount") float discount
			) 
	{
		House house = houseService.findById(house_id);
		if(daysForDiscountValidity<0 || discount>100 || discount<0) {
			request.setAttribute("errorMessage", "Error: invalid data!!");
		}
		else {
		RentAnnouncement rentAnnouncement = new RentAnnouncement(description, house, daysForDiscountValidity, discount/100);
		
		rentAnnouncementService.insert(rentAnnouncement);
		}
		this.setHouses(request);
		
		request.setAttribute("successMessage", "Rent announcement created successfully");
		
		return "houseManagement";
	}
	
	//SET FOR UPDATE OF THE VIEW
	private void setHouses(HttpServletRequest request) {
		Iterable<House> houses = houseService.findAll();
		request.setAttribute("houses", houses);
		
	}
	
	//Check validity of data inserted by user
	private boolean dataIsValid(HttpServletRequest request, float lowSeasonPrice,float mediumSeasonPrice, float highSeasonPrice) {
		if((lowSeasonPrice>=mediumSeasonPrice) || (lowSeasonPrice>=highSeasonPrice) || (mediumSeasonPrice>=highSeasonPrice)) {
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
		
		
		return true;
	}
	
	
	
	

}
