package com.renthouse.service;

import com.renthouse.dao.HouseRepository;
import com.renthouse.dao.RentAnnouncementRepository;
import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentAnnouncementService extends AbstractService<RentAnnouncement>{
	@Autowired
	RentAnnouncementRepository rentAnnouncementRepository;
	@Autowired
	HouseRepository houseRepository;
	
	@Override
	public RentAnnouncement insert(RentAnnouncement rentAnnouncement) {
		
		
		RentAnnouncement newRentAnnouncement = this.rentAnnouncementRepository.save(rentAnnouncement);
		this.associateHouse(newRentAnnouncement, newRentAnnouncement.getReferenceHouse());
		return newRentAnnouncement;
	}
	
	private void associateHouse(RentAnnouncement rentAnnouncement, House house) {
		house.setAnnouncement(rentAnnouncement);
		houseRepository.save(house);
	}
	
	public int[] getNumberOfDaysForEachMonth(Date startDate, Date endDate) {
		int [] numberOfDaysForEachMonth = {0,0,0,0,0,0,0,0,0,0,0,0};
		
		LocalDate startRentDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endRentDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		for (LocalDate date = startRentDate; date.isBefore(endRentDate); date = date.plusDays(1))
		{ 
		  numberOfDaysForEachMonth[date.getMonthValue()-1]++;
		}
		
		numberOfDaysForEachMonth[endRentDate.getMonthValue()-1]++;

		
		return numberOfDaysForEachMonth;
		
	}
	
	public int[] getNumberOfDaysForEachSeason(int[] numberOfDaysForEachMonth) {
		int numberOfDaysForEachSeason[] = {0,0,0};
		
        for(int i=0;i<numberOfDaysForEachMonth.length;i++) {
        	int month = i+1;
        	if((month>=1 && month<4)  || (month>8)) {
        		numberOfDaysForEachSeason[0] = numberOfDaysForEachSeason[0] +numberOfDaysForEachMonth[i];
        	}
        	else if((month>=4 && month<=7)) {
        		numberOfDaysForEachSeason[1] = numberOfDaysForEachSeason[1] +numberOfDaysForEachMonth[i];
        	}
        	else {
        		numberOfDaysForEachSeason[2] =numberOfDaysForEachSeason[2]+ numberOfDaysForEachMonth[i];
        	}
        } 
		return numberOfDaysForEachSeason;
	}
	
	
	public float calculatePrice(RentAnnouncement rentAnnouncement,Date startDate, Date endDate) {
		
	   
		
		int [] numberOfDaysForEachMonth = getNumberOfDaysForEachMonth(startDate, endDate);
		
		int[] numberOfDaysForEachSeason = getNumberOfDaysForEachSeason(numberOfDaysForEachMonth);
		int totalDays = (int) ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant())+1;
		
		float price = (rentAnnouncement.getReferenceHouse().getLowSeasonPrice()*numberOfDaysForEachSeason[0]) +
				(rentAnnouncement.getReferenceHouse().getMediumSeasonPrice()*numberOfDaysForEachSeason[1])+
				(rentAnnouncement.getReferenceHouse().getHighSeasonPrice()*numberOfDaysForEachSeason[2]);
		if(totalDays>rentAnnouncement.getDaysForDiscountValidity()) {
			price = (float) (price-(price*rentAnnouncement.getDiscount()));
		}
		
		
		return price;
	}
	

	

	
	
}
