package com.renthouse.service;
 

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertArrayEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.renthouse.dao.RentAnnouncementRepository;
import com.renthouse.model.House;
import com.renthouse.model.RentAnnouncement;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class RentAnnouncementServiceTest {
	
	@InjectMocks
	private RentAnnouncementService rentAnnouncementService;
	
	@Mock
    private RentAnnouncementRepository rentAnnouncementRepository;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	public void testGetNumberOfDaysForEachMonth() {
	try {
		Date startDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-01");
		Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-11-01");
		int [] res = {0,0,0,0,0,0,0,0,0,31,1,0};
		assertArrayEquals(res, rentAnnouncementService.getNumberOfDaysForEachMonth(startDate, endDate));
		
		startDate =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-06-12");
		endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-12-10");
		int[] res1 = {0,0,0,0,0,19,31,31,30,31,30,10};
		assertArrayEquals(res1, rentAnnouncementService.getNumberOfDaysForEachMonth(startDate, endDate));
		
		startDate =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-11-12");
		endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2021-01-05");
		int[] res2 = {5,0,0,0,0,0,0,0,0,0,19,31};
		assertArrayEquals(res2, rentAnnouncementService.getNumberOfDaysForEachMonth(startDate, endDate));
		
		
		
	}catch(ParseException exc) {
		
	}
	}
	
	@Test 
	public void testGetNumberOfDaysForEachSeason()
	{   
		//Input tale che la prima condizione sia rispettata(bassa stagione)
		int[] numberOfDaysForEachMonth = {5,0,0,0,0,0,0,0,0,0,19,31};
		
		int[] expectedResult = {55,0,0};
		
		assertArrayEquals(expectedResult, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth));
		
		//Input tale che la prima operazione atomica della prima condizione sia rispettata, ossia un periodo tra gennaio e aprile escluso(bassa stagione)
        int[] numberOfDaysForEachMonth1 = {31,15,0,0,0,0,0,0,0,0,0,0};
		
		int[] expectedResult1 = {46,0,0};
		
		assertArrayEquals(expectedResult1, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth1));
		
		//Input tale che la seconda operazione atomica della prima condizione sia rispettata, ossia un periodo tra Settembre e Dicembre
		 int[] numberOfDaysForEachMonth2 = {0,0,0,0,0,0,0,0,0,30,20,0};
			
		int[] expectedResult2 = {50,0,0};
			
		assertArrayEquals(expectedResult2, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth2));
		
		//Periodo tra aprile e luglio (media stagione)
		int[] numberOfDaysForEachMonth3 = {0,0,0,30,31,30,10,0,0,0,0,0};
		
		int[] expectedResult3 = {0,101,0};
			
		assertArrayEquals(expectedResult3, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth3));
		

		//Periodo di agosto(alta stagione)
		int[] numberOfDaysForEachMonth4 = {0,0,0,0,0,0,0,10,0,0,0,0};
		
		int[] expectedResult4 = {0,0,10};
			
		assertArrayEquals(expectedResult4, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth4));
		
		//Periodo a cavallo tra bassa e media stagione
		int[] numberOfDaysForEachMonth5 = {0,0,31,20,0,0,0,0,0,0,0,0};
		
		int[] expectedResult5 = {31,20,0};
			
		assertArrayEquals(expectedResult5, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth5));
		
		//Periodo a cavallo tra bassa e alta stagione
		int[] numberOfDaysForEachMonth6 = {0,0,0,0,0,0,0,31,14,0,0,0};
		
		int[] expectedResult6 = {14,0,31};
			
		assertArrayEquals(expectedResult6, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth6));
		
		//Periodo a cavallo tra media e alta stagione
		int[] numberOfDaysForEachMonth7 = {0,0,0,0,0,0,14,31,0,0,0,0};
		
		int[] expectedResult7 = {0,14,31};
			
		assertArrayEquals(expectedResult7, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth7));
		
		
		
		
		
		
		
		
		
	}
	
	@Test
	public void testCalculatePrice() {
		try {
			Date startDate =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-11-12");
			Date endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2021-01-05");
			House house = new House();
			house.setLowSeasonPrice(15);
			house.setMediumSeasonPrice(20);
			house.setHighSeasonPrice(30);
			RentAnnouncement rentAnnouncement = new RentAnnouncement();
			rentAnnouncement.setReferenceHouse(house);
			assertThat(rentAnnouncementService.calculatePrice(rentAnnouncement, startDate, endDate)==1575);
			
		}catch(ParseException pe) {}
	}
}
