package com.rent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="payments",
uniqueConstraints = 
@UniqueConstraint(columnNames = {"rentalApp_id","payment_month"}))
public class PaymentEntity extends BaseEntity{
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="rentalApp_id",nullable = false)
	private RentalApplicationEntity rentalApplication;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_month",nullable = false)
	private PaymentMonth month;
	
	@Column(name="amount",nullable = false)
	private double amount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_status",nullable = false)
	private PaymentStatus status;

	public PaymentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentEntity(RentalApplicationEntity rentalApplication, PaymentMonth month, double amount) {
		super();
		this.rentalApplication = rentalApplication;
		this.month = month;
		this.amount = amount;
		this.status = PaymentStatus.PENDING;
	}

	public RentalApplicationEntity getRentalApplication() {
		return rentalApplication;
	}

	public void setRentalApplication(RentalApplicationEntity rentalApplication) {
		this.rentalApplication = rentalApplication;
	}

	public PaymentMonth getMonth() {
		return month;
	}

	public void setMonth(PaymentMonth month) {
		this.month = month;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PaymentEntity [rentalApplication=" + rentalApplication + ", month=" + month + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
	
	
	
	
	

}
