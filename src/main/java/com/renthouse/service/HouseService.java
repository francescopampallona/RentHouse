package com.renthouse.service;


import com.querydsl.core.BooleanBuilder;
import com.renthouse.dao.HouseRepository;
import com.renthouse.model.House;

import com.renthouse.model.QHouse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class HouseService extends AbstractService<House> {
	
	@Autowired
	private HouseRepository houseRepository;
	
	public Iterable<House> searchByParams(String nation, String city, Optional<Integer> maxNumberOfGuests, String address, @RequestParam Optional<Integer> civicNumber) {
	     BooleanBuilder where = new BooleanBuilder();
	    
	     if(!nation.trim().equals("")) {
	    	 where.and(QHouse.house.nation.eq(nation));
	     }
	     
	     if(!city.trim().equals("")) {
	    	 where.and(QHouse.house.city.eq(city));
	     }
	     
	     if(maxNumberOfGuests.isPresent()) {
	    	 where.and(QHouse.house.maxGuests.eq(maxNumberOfGuests.get()));
	     }
	     
	     if(!address.trim().equals("")) {
	    	 where.and(QHouse.house.address.eq(address));
	     }
	     
	     if(civicNumber.isPresent()) {
	    	 where.and(QHouse.house.civicNumber.eq(civicNumber.get()));
	     }
	     
		return this.houseRepository.findAll(where);
	}
	
	

}
