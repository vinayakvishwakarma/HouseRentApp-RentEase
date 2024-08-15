package com.rent.entities;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="properties")
public class PropertyEntity extends BaseEntity {

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="landlord_id", nullable = false)
	private UserEntity landlord;
	
	@Column(length=200,name="address",nullable = false)
	private String address;
	
	@Column(name="area", nullable = false)
	private String area;
	
	@Column(name="city",nullable = false)
	private String city;
	
	@Column(name="state",nullable = false)
	private String state;
	
	@Column(name="description")
	private String description;
	
	@Column(name="amenities",nullable = false)
	private String amenities;
	
	@Column(name="rent",nullable = false)
	private double rent;

	/*
	 * @Lob
	 * 
	 * @Column(name="image") private byte[] image;
	 */
	public PropertyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PropertyEntity(UserEntity landlord, String address, String area, String city, String state,
			String description, String amenities, double rent /*byte[] image*/) {
		super();
		this.landlord = landlord;
		this.address = address;
		this.area = area;
		this.city = city;
		this.state = state;
		this.description = description;
		this.amenities = amenities;
		this.rent = rent;
		//this.image = image;
	}
	public UserEntity getLandlord() {
		return landlord;
	}
	public void setLandlord(UserEntity landlord) {
		this.landlord = landlord;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	@Override
	public String toString() {
		return "PropertyEntity [landlord=" + landlord + ", address=" + address + ", area=" + area + ", city=" + city
				+ ", state=" + state + ", description=" + description + ", amenities=" + amenities + ", rent=" + rent
				+ "]";
	}

	/*
	 * public byte[] getImage() { return image; } public void setImage(byte[] image)
	 * { this.image = image; }
	 */
	
	
	
	
	
	
	
	
}
