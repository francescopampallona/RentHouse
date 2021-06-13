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
	private float price;
	
	@ManyToOne
	private House referenceHouse;
	
	@ManyToOne
	private Host referenceHost;
	
	//DEFAULT CONSTRUCTOR
	public Reservation() {}
	
	//CONSTRUCTOR
	public Reservation(
			Date startReservation, 
			Date endReservation, 
			float price,
			House referenceHouse,
			Host host) {
		this.startReservation = startReservation;
		this.endReservation = endReservation;
		this.price = price;
		this.referenceHouse = referenceHouse;
		this.referenceHost = host;
	}
	
	public House getReferenceHouse() {
		return referenceHouse;
	}

	public void setReferenceHouse(House referenceHouse) {
		this.referenceHouse = referenceHouse;
	}
	
	public Host getReferenceHost() {
		return referenceHost;
	}

	public void setReferenceHost(Host referenceHost) {
		this.referenceHost = referenceHost;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
}
