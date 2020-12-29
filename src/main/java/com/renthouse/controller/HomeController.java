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

import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;
import com.renthouse.service.HouseService;
import com.renthouse.service.RentAnnouncementService;
import com.renthouse.service.ReservationService;

@Controller
@RequestMapping("/")
public class HomeController {
	

	@Autowired
	RentAnnouncementService rentAnnouncementService;

	@Autowired
	HouseService houseService;
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping
	public String announcements(HttpServletRequest request, @RequestParam Optional<String> nation,
			@RequestParam Optional<String> city, @RequestParam Optional<Integer> maxNumberOfGuests) {

		ArrayList<RentAnnouncement> announcements = new ArrayList<RentAnnouncement>();

		if (nation.isPresent() && city.isPresent()) {

			Iterable<House> houses = this.houseService.searchByParams(nation.get(), city.get(), maxNumberOfGuests);

			for (House house : houses) {

				if (house.getAnnouncement() != null) {
					announcements.add(house.getAnnouncement());
				}
			}

		} else {
			announcements = (ArrayList<RentAnnouncement>) this.rentAnnouncementService.findAll();
		}

		int numberOfElements = announcements.size();
		request.setAttribute("announcements", announcements);
		request.setAttribute("numberOfElements", numberOfElements);

		return "index";
	}
	
	@GetMapping("/announcement/{announcement_id}")
    public String details(HttpServletRequest request, @PathVariable("announcement_id") long id) {
		RentAnnouncement announcement = rentAnnouncementService.findById(id);
		request.setAttribute("announcement", announcement);
		return "rentAnnouncementDetails";
	}

	@PostMapping("/announcement/{announcement_id}")
	public String calculatePrice(HttpServletRequest request, @PathVariable("announcement_id") long id,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
		
		try {
			Date startRentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(startDate);
			Date endRentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDate);
			//-------------------------
			//Check validity of data inserted
			if(!checkValidity(startRentDate, endRentDate)) {
				request.setAttribute("announcement", this.rentAnnouncementService.findById(id));
				request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
				return "rentAnnouncementDetails";
			}
			//-------------------------
			RentAnnouncement announcement = rentAnnouncementService.findById(id);
			float price = rentAnnouncementService.calculatePrice(announcement, startRentDate, endRentDate);

			request.setAttribute("announcement", announcement);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("totalPrice" + id, price);
			request.setAttribute("totalDays" + id,
					ChronoUnit.DAYS.between(startRentDate.toInstant(), endRentDate.toInstant()) + 1);
			if(this.reservationService.selectedPeriodIsAlreadyBooked(announcement.getReferenceHouse().getId(), startRentDate, endRentDate)) {
				request.setAttribute("WARNING", "Warning: the selected period goes in conflict with the period of another reservation");
			}
		} catch (ParseException exc) {
			request.setAttribute("announcement", this.rentAnnouncementService.findById(id));
			request.setAttribute("errorMessage", exc);
			return "rentAnnouncementDetails";
		}
		return "rentAnnouncementDetails";
	}
	
	private boolean checkValidity(Date startRentDate, Date endRentDate) {
		if(startRentDate.after(endRentDate)) {
			return false;
		}
		return true;
	}
	

}
