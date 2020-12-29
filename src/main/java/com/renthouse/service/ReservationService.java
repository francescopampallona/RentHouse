package com.renthouse.service;

import com.renthouse.dao.ReservationRepository;
import com.renthouse.model.Reservation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReservationService extends AbstractService<Reservation> {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<Reservation> getByHouseId(long houseId){
		return (List<Reservation>)this.reservationRepository.getByReferenceHouse_Id(houseId);
	}
	
	public boolean selectedPeriodIsAlreadyBooked(long houseId, Date selectedStartDate, Date selectedEndDate){
		
		List<Reservation> reservations = (List<Reservation>)this.reservationRepository.getByReferenceHouse_Id(houseId);
		boolean result = reservations.
				          stream().
				          anyMatch(res-> 
		                                (res.getStartReservation().before(selectedEndDate) && res.getEndReservation().after(selectedEndDate)) ||
		                                (res.getStartReservation().before(selectedStartDate) && res.getEndReservation().after(selectedStartDate)) ||
		                                (res.getStartReservation().after(selectedStartDate) && res.getEndReservation().before(selectedEndDate)) ||
		                                (res.getStartReservation().compareTo(selectedEndDate)==0)||(res.getEndReservation().compareTo(selectedStartDate)==0)||
		                                (res.getStartReservation().compareTo(selectedStartDate)==0 || res.getEndReservation().compareTo(selectedEndDate)==0));
		return result;
	}
	
	//In case of update, check that IDs are not the same 
	public boolean selectedPeriodIsAlreadyBooked(long reservationId,long houseId, Date selectedStartDate, Date selectedEndDate){
		
		List<Reservation> reservations = (List<Reservation>)this.reservationRepository.getByReferenceHouse_Id(houseId);
		boolean result = reservations.
				          stream().
				          anyMatch(res-> (res.getId()!=reservationId) && (
		                                (res.getStartReservation().before(selectedEndDate) && res.getEndReservation().after(selectedEndDate)) ||
		                                (res.getStartReservation().before(selectedStartDate) && res.getEndReservation().after(selectedStartDate)) ||
		                                (res.getStartReservation().after(selectedStartDate) && res.getEndReservation().before(selectedEndDate)) ||
		                                (res.getStartReservation().compareTo(selectedEndDate)==0)||(res.getEndReservation().compareTo(selectedStartDate)==0)||
		                                (res.getStartReservation().compareTo(selectedStartDate)==0 || res.getEndReservation().compareTo(selectedEndDate)==0)));
		return result;
	}

}
