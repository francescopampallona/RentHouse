package com.renthouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="houses", 
       uniqueConstraints= {
    		   @UniqueConstraint(columnNames={"nation", "city", "address", "civicNumber"})})
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nation;
	
	@Column
	private String city;
	
	@Column
	private String address;
	
	@Column
	private int civicNumber;
	
	@Column
	private int maxGuests;
	
	@Column
	private float lowSeasonPrice;
	
	@Column
	private String lowSeasonStartMonth;
	
	@Column
	private float mediumSeasonPrice;
	
	@Column
	private String mediumSeasonStartMonth;
	
	@Column
	private float highSeasonPrice;
	
	@Column
	private String highSeasonStartMonth;
	
	@OneToOne
	private RentAnnouncement announcement;
	


	@ManyToOne
	private User owner;
	
	
	
	//Constructors
	public House() {}
	
	public House(String nation, String city, String address, int civicNumber, int maxGuests, float lowSeasonPrice, String lowSeasonStartMonth, float mediumSeasonPrice,String mediumSeasonStartMonth, float highSeasonPrice, String highSeasonStartMonth, User owner) {
		this.nation = nation;
		this.city = city;
		this.address = address;
		this.civicNumber = civicNumber;
		this.maxGuests = maxGuests;
		this.lowSeasonPrice = lowSeasonPrice;
		this.lowSeasonStartMonth = lowSeasonStartMonth;
		this.mediumSeasonPrice = mediumSeasonPrice;
		this.mediumSeasonStartMonth = mediumSeasonStartMonth;
		this.highSeasonPrice = highSeasonPrice;
		this.highSeasonStartMonth = highSeasonStartMonth;
		this.owner = owner;
	}
	
	
    
	
	//Getter and setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCivicNumber() {
		return civicNumber;
	}

	public void setCivicNumber(int civicNumber) {
		this.civicNumber = civicNumber;
	}

	public int getMaxGuests() {
		return maxGuests;
	}

	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}

	public float getLowSeasonPrice() {
		return lowSeasonPrice;
	}

	public void setLowSeasonPrice(float lowSeasonPrice) {
		this.lowSeasonPrice = lowSeasonPrice;
	}

	public float getMediumSeasonPrice() {
		return mediumSeasonPrice;
	}

	public void setMediumSeasonPrice(float mediumSeasonPrice) {
		this.mediumSeasonPrice = mediumSeasonPrice;
	}

	public float getHighSeasonPrice() {
		return highSeasonPrice;
	}

	public void setHighSeasonPrice(float highSeasonPrice) {
		this.highSeasonPrice = highSeasonPrice;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getLowSeasonStartMonth() {
		return lowSeasonStartMonth;
	}

	public void setLowSeasonStartMonth(String lowSeasonStartMonth) {
		this.lowSeasonStartMonth = lowSeasonStartMonth;
	}

	public String getMediumSeasonStartMonth() {
		return mediumSeasonStartMonth;
	}

	public void setMediumSeasonStartMonth(String mediumSeasonStartMonth) {
		this.mediumSeasonStartMonth = mediumSeasonStartMonth;
	}

	public String getHighSeasonStartMonth() {
		return highSeasonStartMonth;
	}

	public void setHighSeasonStartMonth(String highSeasonStartMonth) {
		this.highSeasonStartMonth = highSeasonStartMonth;
	}

	public RentAnnouncement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(RentAnnouncement announcement) {
		this.announcement = announcement;
	}

	
	
}
