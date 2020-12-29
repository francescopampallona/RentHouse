package com.renthouse.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.renthouse.dao.ReservationRepository;
import com.renthouse.model.House;
import com.renthouse.model.Reservation;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ReservationServiceTest {
	
	@InjectMocks
	private ReservationService reservationService;
	
	@Mock
	private ReservationRepository reservationRepository;
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSelectedPeriodIsAlreadyBooked() {
		
		
		
		try {
        Iterable<Reservation> reservationList = this.getReservationList();
		when(this.reservationRepository.getByReferenceHouse_Id(Mockito.anyLong())).thenReturn(reservationList);
		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
			                                                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-20"),
			                                                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-01")));
		

		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
			                                                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-01"),
			                                                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-05")));
		
		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-08"),
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-15")));
		
		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-01"),
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-28")));
		
		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-10"),
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-28")));
		
		assertTrue(this.reservationService.selectedPeriodIsAlreadyBooked(0, 
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-28"),
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-10")));
		
		assertFalse(this.reservationService.selectedPeriodIsAlreadyBooked(0,
				                            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-08"), 
				                            new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-18")));
		
		assertFalse(this.reservationService.selectedPeriodIsAlreadyBooked(0,
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-11-08"), 
                new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-11-18")));
		
		
		
		
		}catch(ParseException ex) {}
		
		
	}
	
	private Iterable<Reservation> getReservationList() throws ParseException{
		List<Reservation> reservations = new ArrayList<>();
		
		reservations.add(new Reservation( new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-09-28").getTime()),
			                              new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse("2020-10-10").getTime()),
				                         new House(),
				                         "aaa",
				                         "aaa",
				                         "aaa@aaa",
				                         "333",
				                         "aaa"));
		
		return reservations;
		
		}
	

}
