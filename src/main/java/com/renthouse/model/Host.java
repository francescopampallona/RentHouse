package com.renthouse.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hosts")
public class Host {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String nation;
	
	@Column 
	private String birthCity;
	
	@Column
	private Date birthDate;
	
	@Column
	private String email;
	
	@Column 
	private String telephoneNumber;
	
	@Column
	private String creditCardNumber;
	
	//Constructors
	public Host() {}
	
	public Host(String name, String surname, String nation, String birthCity, Date birthDate, String email, String telephoneNumber, String creditCardNumber) {
		this.name = name;
		this.surname = surname;
		this.nation = nation;
		this.birthCity = birthCity;
		this.birthDate = birthDate;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.creditCardNumber = creditCardNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
	
	
	
}
