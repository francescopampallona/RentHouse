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
import com.renthouse.service.HouseService;
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
	private ReservationService reservationService;
	
	
	
	@GetMapping("/{houseId}/")
	public String getAll(HttpServletRequest request, @PathVariable("houseId") long houseId) {
		
	    this.setReservations(request, houseId);
		request.setAttribute("houseId", houseId);
		
		
		return "reservationManagement";
	}
	
	@PostMapping("/insert/{houseId}")
	public String insert(
			HttpServletRequest request,
			@PathVariable("houseId") long houseId,
			@RequestParam("startReservation") Date startReservation,
			@RequestParam("endReservation") Date endReservation,
			@RequestParam("potentialHostName") String potentialHostName,
			@RequestParam("potentialHostSurname") String potentialHostSurname,
			@RequestParam("potentialHostEmail") String potentialHostEmail,
			@RequestParam("potentialHostTelephoneNumber") String potentialHostTelephoneNumber,
			@RequestParam("potentialHostCreditCardNumber") String potentialHostCreditCardNumber
			) {
		if(!this.checkValidity(startReservation, endReservation )) {

			request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
		}
	    else if(this.reservationService.selectedPeriodIsAlreadyBooked(houseId, startReservation,endReservation)) {
			request.setAttribute("errorMessage", "Error: the selected period goes in conflict with the period of another reservation");
		}
		else {
		this.reservationService.insert(new Reservation(startReservation, endReservation,(House)this.houseService.findById(houseId), potentialHostName, potentialHostSurname, potentialHostEmail, potentialHostTelephoneNumber, potentialHostCreditCardNumber));
		request.setAttribute("successMessage", "Reservation created successfully");
		}
		
		this.setReservations(request, houseId);
		request.setAttribute("houseId", houseId);
		return "reservationManagement";
	}
	
	@PostMapping("/update/{id}")
	public String update(
			HttpServletRequest request,
			@PathVariable("id") long id,
			@RequestParam("startReservation") Date startReservation,
			@RequestParam("endReservation") Date endReservation,
			@RequestParam("potentialHostName") String potentialHostName,
			@RequestParam("potentialHostSurname") String potentialHostSurname,
			@RequestParam("potentialHostEmail") String potentialHostEmail,
			@RequestParam("potentialHostTelephoneNumber") String potentialHostTelephoneNumber,
			@RequestParam("potentialHostCreditCardNumber") String potentialHostCreditCardNumber
			) {
		Reservation reservationToUpdate = this.reservationService.findById(id);
		reservationToUpdate.setStartReservation(startReservation);
		reservationToUpdate.setEndReservation(endReservation);
		reservationToUpdate.setPotentialHostName(potentialHostName);
		reservationToUpdate.setPotentialHostSurname(potentialHostSurname);
		reservationToUpdate.setPotentialHostEmail(potentialHostEmail);
		reservationToUpdate.setPotentialHostTelephoneNumber(potentialHostTelephoneNumber);
		reservationToUpdate.setPotentialHostCreditCardNumber(potentialHostCreditCardNumber);
		long houseId = reservationToUpdate.getReferenceHouse().getId();
		if(!this.checkValidity(startReservation, endReservation )) {

			request.setAttribute("errorMessage", "Error: start date must be before or equal end date");
		}
		else if(this.reservationService.selectedPeriodIsAlreadyBooked(id, houseId, startReservation,endReservation)) {
			request.setAttribute("errorMessage", "Error: the selected period goes in conflict with the period of another reservation");
		}
		else {
		this.reservationService.update(reservationToUpdate);
		request.setAttribute("successMessage", "Reservation updated successfully");
		}
		this.setReservations(request, houseId);
		request.setAttribute("houseId", houseId);
		return "reservationManagement";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(HttpServletRequest request, @PathVariable("id") long id) {
		Reservation reservationToDelete = this.reservationService.findById(id);
		long houseId = reservationToDelete.getReferenceHouse().getId();
		this.reservationService.delete(id);
		request.setAttribute("successMessage", "Reservation deleted successfully");
		this.setReservations(request, houseId);
		request.setAttribute("houseId", houseId);
		
		return "reservationManagement";
	}

	
	private void setReservations(HttpServletRequest request, long houseId) {
		List<Reservation> reservations= this.reservationService.getByHouseId(houseId);
		request.setAttribute("reservations", reservations);
	}
	
	private boolean checkValidity(Date startRentDate, Date endRentDate) {
		if(startRentDate.after(endRentDate)) {
			return false;
		}
		return true;
	}
	
	

}
