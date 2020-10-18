package com.renthouse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renthouse.model.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long> {
	
	public Iterable<House> findByNation(String nation);
	
	public Iterable<House> findByCity(String city);
	
	public Iterable<House> findByNationAndCity(String nation, String city);
	
}
