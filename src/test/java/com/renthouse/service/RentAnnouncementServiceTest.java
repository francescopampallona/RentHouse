package com.renthouse.service;
 

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
		int[] numberOfDaysForEachMonth = {5,0,0,0,0,0,0,0,0,0,19,31};
		
		int[] expectedResult = {55,0,0};
		
		assertArrayEquals(expectedResult, rentAnnouncementService.getNumberOfDaysForEachSeason(numberOfDaysForEachMonth));
		
		
	}
}
