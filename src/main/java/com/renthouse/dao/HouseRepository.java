package com.renthouse.dao;


import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.renthouse.model.House;

@Repository
public interface HouseRepository extends CrudRepository<House, Long>, QuerydslPredicateExecutor{
	
	
	
}
