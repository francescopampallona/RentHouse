package com.renthouse.service;

import com.renthouse.dao.HouseRepository;
import com.renthouse.exceptions.EmptyParamsException;
import com.renthouse.model.House;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseService extends AbstractService<House> {
	
	@Autowired
	private HouseRepository houseRepository;
	
	public Iterable<House> searchByParams(String nation, String city) throws EmptyParamsException{
		Iterable<House> houses;
		if((!nation.trim().equals("")&&nation!=null) && (!city.trim().equals("") && city!=null)) {
			
			houses = this.houseRepository.findByNationAndCity(nation, city);
		}
		else if(!nation.trim().equals("")&&nation!=null) {
			
			houses = this.houseRepository.findByNation(nation);
		}
		else if(!city.trim().equals("") && city!=null) {
			
			houses = this.houseRepository.findByCity(city);
		}
		else {
			throw new EmptyParamsException("Exception: the parameters are not specified!");
		}
		return houses;
	}
	
	

}
