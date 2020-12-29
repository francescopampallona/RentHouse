package com.renthouse.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private Date startReservation;
	
	@Column
	private Date endReservation;
	
	@Column 
	private String potentialHostName;
	
	@Column 
	private String potentialHostSurname;
	
	@Column
	private String potentialHostEmail;
	
	@Column
	private String potentialHostTelephoneNumber;
	
	@Column
	private String potentialHostCreditCardNumber;
	
	@ManyToOne
	private House referenceHouse;
	
	 
	
	
	//DEFAULT CONSTRUCTOR
	public Reservation() {}
	
	//CONSTRUCTOR
	public Reservation(
			Date startReservation, 
			Date endReservation, 
			House referenceHouse, 
			String potentialHostName,
			String potentialHostSurname,
			String potentialHostEmail,
			String potentialHostTelephoneNumber,
			String potentialHostCreditCardNumber) {
		this.startReservation = startReservation;
		this.endReservation = endReservation;
		this.referenceHouse = referenceHouse;
		this.potentialHostName = potentialHostName;
		this.potentialHostSurname = potentialHostSurname;
		this.potentialHostEmail = potentialHostEmail;
		this.potentialHostTelephoneNumber = potentialHostTelephoneNumber;
		this.potentialHostCreditCardNumber = potentialHostCreditCardNumber;
		
	}
	
	public String getPotentialHostName() {
		return potentialHostName;
	}

	public void setPotentialHostName(String potentialHostName) {
		this.potentialHostName = potentialHostName;
	}

	public String getPotentialHostSurname() {
		return potentialHostSurname;
	}

	public void setPotentialHostSurname(String potentialHostSurname) {
		this.potentialHostSurname = potentialHostSurname;
	}

	public String getPotentialHostEmail() {
		return potentialHostEmail;
	}

	public void setPotentialHostEmail(String potentialHostEmail) {
		this.potentialHostEmail = potentialHostEmail;
	}

	public String getPotentialHostTelephoneNumber() {
		return potentialHostTelephoneNumber;
	}

	public void setPotentialHostTelephoneNumber(String potentialHostTelephoneNumber) {
		this.potentialHostTelephoneNumber = potentialHostTelephoneNumber;
	}

	public String getPotentialHostCreditCardNumber() {
		return potentialHostCreditCardNumber;
	}

	public void setPotentialHostCreditCardNumber(String potentialHostCreditCardNumber) {
		this.potentialHostCreditCardNumber = potentialHostCreditCardNumber;
	}



	public House getReferenceHouse() {
		return referenceHouse;
	}

	public void setReferenceHouse(House referenceHouse) {
		this.referenceHouse = referenceHouse;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStartReservation() {
		return startReservation;
	}

	public void setStartReservation(Date startReservation) {
		this.startReservation = startReservation;
	}

	public Date getEndReservation() {
		return endReservation;
	}

	public void setEndReservation(Date endReservation) {
		this.endReservation = endReservation;
	}
	
	
	
	
}
