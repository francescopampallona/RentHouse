package com.renthouse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rent_announcement")
public class RentAnnouncement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String description;
	

	@OneToOne
	private House referenceHouse;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public House getReferenceHouse() {
		return referenceHouse;
	}

	public void setReferenceHouse(House referenceHouse) {
		this.referenceHouse = referenceHouse;
	}

	

}
