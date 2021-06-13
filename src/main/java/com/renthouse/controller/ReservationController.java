package com.renthouse.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.House;
import com.renthouse.model.Reservation;
import com.renthouse.model.Host;
import com.renthouse.service.HostService;
import com.renthouse.service.HouseService;
import com.renthouse.service.RentAnnouncementService;
import com.renthouse.service.ReservationService;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private HostService hostService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RentAnnouncementService rentAnnouncementService;
	
	
	
	@GetMapping("/{houseId}/")
	public String getAll(HttpServletRequest request, @PathVariable("houseId") long houseId) {
		
	    this.setUpdates(request, houseId);
		return "reservationManagement";
	}
	
	
	@PostMapping("/insert/{houseId}")
	public String insert(
			HttpServletRequest request,
			@PathVariable("houseId") long houseId,
			@RequestParam("startReservation") Date startReservation,
			@RequestParam("endReservation") Date endReservation,
			@RequestParam("hostId") long hostId
			) {
		if(!this.checkValidity(startReservation, endReservation )) {

			request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
		}
	    else if(this.reservationService.selectedPeriodIsAlreadyBooked(houseId, startReservation,endReservation)) {
			request.setAttribute("errorMessage", "Error: the selected period goes in conflict with the period of another reservation");
		}
		else {
	    House house = this.houseService.findById(houseId);
		float price = this.rentAnnouncementService.calculatePrice(house.getAnnouncement(),new java.util.Date(startReservation.getTime()),new java.util.Date(endReservation.getTime()));
		this.reservationService.insert(new Reservation(startReservation, endReservation, price,house, (Host)this.hostService.findById(hostId) ));
		request.setAttribute("successMessage", "Reservation created successfully");
		}
		
		this.setUpdates(request, houseId);
		return "reservationManagement";
	}
	
	
	@PostMapping("/update/{id}")
	public String update(
			HttpServletRequest request,
			@PathVariable("id") long id,
			@RequestParam("startReservation") Date startReservation,
			@RequestParam("endReservation") Date endReservation
			) {
		Reservation reservationToUpdate = this.reservationService.findById(id);
		reservationToUpdate.setStartReservation(startReservation);
		reservationToUpdate.setEndReservation(endReservation);
		long houseId = reservationToUpdate.getReferenceHouse().getId();
		if(!this.checkValidity(startReservation, endReservation )) {

			request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
		}
		else if(this.reservationService.selectedPeriodIsAlreadyBooked(id, houseId, startReservation,endReservation)) {
			request.setAttribute("errorMessage", "Error: the selected period goes in conflict with the period of another reservation");
		}
		else {
		House house = this.houseService.findById(houseId);
		float price = this.rentAnnouncementService.calculatePrice(house.getAnnouncement(),new java.util.Date(startReservation.getTime()),new java.util.Date(endReservation.getTime()));
		reservationToUpdate.setPrice(price);	
		this.reservationService.update(reservationToUpdate);
		request.setAttribute("successMessage", "Reservation updated successfully");
		}
		this.setUpdates(request, houseId);
		return "reservationManagement";
		
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(HttpServletRequest request, @PathVariable("id") long id) {
		Reservation reservationToDelete = this.reservationService.findById(id);
		long houseId = reservationToDelete.getReferenceHouse().getId();
		this.reservationService.delete(id);
		request.setAttribute("successMessage", "Reservation deleted successfully");
		this.setUpdates(request, houseId);
		return "reservationManagement";
	}
	
	
	@PostMapping("/checkPeriodAvailability/{houseId}")
	public String checkPeriodAvailability(HttpServletRequest request, 
			                              @PathVariable("houseId") long houseId,
			                              @RequestParam("startReservation") Date startReservation,
			                              @RequestParam("endReservation") Date endReservation) {
	   if(!this.checkValidity(startReservation,endReservation)) {
		   request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
		}
	   else if(this.reservationService.selectedPeriodIsAlreadyBooked(houseId, startReservation, endReservation)) {
			request.setAttribute("CONFLICT", "From " +startReservation+" to "+endReservation+" in conflict with the period of another reservation!");
		}
		else {
			request.setAttribute("AVAILABLE", "From "+startReservation+" to "+endReservation+" is totally available!");
		}
		
		this.setUpdates(request, houseId);
		return "reservationManagement";
	}

	
	private void setUpdates(HttpServletRequest request, long houseId) {
		List<Reservation> reservations= this.reservationService.getByHouseId(houseId);
		List<Host> hosts = (List<Host>)this.hostService.findAll();
		House house = this.houseService.findById(houseId);
		request.setAttribute("reservations", reservations);
		request.setAttribute("hosts", hosts);
		request.setAttribute("house", house);
		request.setAttribute("houseId", houseId);
	}
	
	private boolean checkValidity(Date startRentDate, Date endRentDate) {
		if(startRentDate.after(endRentDate)) {
			return false;
		}
		return true;
	}
	
	

}
