package com.rent.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="rental_app")
public class RentalApplicationEntity extends BaseEntity {
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="property_id",nullable = false)
	private PropertyEntity property;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id",nullable = false)
	private UserEntity tenant;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable = false)
	private RentalStatus status;

	public RentalApplicationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RentalApplicationEntity(PropertyEntity property, UserEntity tenant) {
		super();
		this.property = property;
		this.tenant = tenant;
		this.status = RentalStatus.PENDING;
	}

	public PropertyEntity getProperty() {
		return property;
	}

	public void setProperty(PropertyEntity property) {
		this.property = property;
	}

	public UserEntity getTenant() {
		return tenant;
	}

	public void setTenant(UserEntity tenant) {
		this.tenant = tenant;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void setStatus(RentalStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RentalApplicationEntity [property=" + property + ", tenant=" + tenant + ", status=" + status + "]";
	}
	
	
	
}
