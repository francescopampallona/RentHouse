package com.renthouse.dao;


import java.sql.Date;

import org.springframework.data.repository.CrudRepository;

import com.renthouse.model.Reservation;



public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	public Iterable<Reservation> getByReferenceHouse_Id(long houseId);
	
	
	
}
