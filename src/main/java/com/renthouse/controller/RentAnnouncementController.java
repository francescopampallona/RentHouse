package com.renthouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;
import com.renthouse.service.HouseService;
import com.renthouse.service.RentAnnouncementService;

@Controller
@RequestMapping("/rentAnnouncement")
public class RentAnnouncementController {
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private RentAnnouncementService rentAnnouncementService;
	
	@PostMapping("/update/{id}")
	public String update(HttpServletRequest request,
			@RequestParam("description") String description,
			@PathVariable("id") long id) 
	{
		RentAnnouncement rentAnnouncementToUpdate = rentAnnouncementService.findById(id);
		
		rentAnnouncementToUpdate.setDescription(description);
		rentAnnouncementService.update(rentAnnouncementToUpdate);
		
		this.setHouses(request);
		
		
		request.setAttribute("successMessage", "Rent announcement updated successfully");
		
		return "houseManagement";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(HttpServletRequest request, @PathVariable("id") long id) {
		
		rentAnnouncementService.delete(id);
		
		this.setHouses(request);
		
		request.setAttribute("successMessage", "Rent announcement deleted successfully");
		
		return "houseManagement";
	}
	
	
	
	//SET FOR UPDATE OF THE VIEW
	private void setHouses(HttpServletRequest request) {
		Iterable<House> houses = houseService.findAll();
		request.setAttribute("houses", houses);
			
		}

}
