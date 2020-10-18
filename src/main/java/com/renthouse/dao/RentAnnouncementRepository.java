package com.renthouse.dao;

import org.springframework.data.repository.CrudRepository;

import com.renthouse.model.RentAnnouncement;


public interface RentAnnouncementRepository extends  CrudRepository<RentAnnouncement, Long> {
	
	
}
