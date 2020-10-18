package com.renthouse.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.exceptions.EmptyParamsException;
import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;
import com.renthouse.service.HouseService;
import com.renthouse.service.RentAnnouncementService;


@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	RentAnnouncementService rentAnnouncementService;
	
	@Autowired 
	HouseService houseService;
	
	@GetMapping
	public String announcements(HttpServletRequest request, @RequestParam Optional<String> nation, @RequestParam Optional<String> city) {

		
		ArrayList<RentAnnouncement> announcements =new ArrayList<RentAnnouncement>();
		
		if(nation.isPresent() && city.isPresent()) {
			
			try {
				Iterable<House> houses= this.houseService.searchByParams(nation.get(), city.get());
				
				for(House house: houses) {
					
					if(house.getAnnouncement()!=null) {
						announcements.add(house.getAnnouncement());
					}
				}
			}
			catch(EmptyParamsException exc) {
			announcements = (ArrayList<RentAnnouncement>)this.rentAnnouncementService.findAll();
			
			}
		}
		else {
			announcements = (ArrayList<RentAnnouncement>)this.rentAnnouncementService.findAll();
		}
		
		int numberOfElements = announcements.size();
		request.setAttribute("announcements", announcements);
		request.setAttribute("numberOfElements", numberOfElements);
		

		return "index";
	}
	
	@PostMapping("/calculatePrice/{announcement_id}")
	public String calculatePrice(HttpServletRequest request,
			@PathVariable("announcement_id") long id,
			@RequestParam("startDate")String  startDate,
			@RequestParam("endDate") String  endDate) {
		try {
		Date startRentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(startDate);
		Date endRentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDate);
		
		RentAnnouncement announcement = rentAnnouncementService.findById(id);
		float price = rentAnnouncementService.calculatePrice(announcement, startRentDate, endRentDate);
		
		request.setAttribute("announcement", announcement);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("totalPrice"+id, price);
		request.setAttribute("totalDays"+id,  ChronoUnit.DAYS.between(startRentDate.toInstant(), endRentDate.toInstant())+1);
		
		
		}catch(ParseException exc) {
			request.setAttribute("announcements", this.rentAnnouncementService.findAll());
			request.setAttribute("errorMessage", exc);
			return "index";
		}
		return "rentAnnouncementDetails";
	}

}
