package com.rent.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_review")
public class UserReviewEntity extends BaseEntity {
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id",nullable = false)
	private UserEntity tenant;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="property_id",nullable = false)
	private PropertyEntity property;
	
	@Column(name="ratings",nullable = false)
	private Integer rating;
	
	@Column(name="comments",nullable = false)
	private String comments;

	public UserReviewEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserReviewEntity(UserEntity tenant, PropertyEntity property, Integer rating, String comments) {
		super();
		this.tenant = tenant;
		this.property = property;
		this.rating = rating;
		this.comments = comments;
	}

	public UserEntity getTenant() {
		return tenant;
	}

	public void setTenant(UserEntity tenant) {
		this.tenant = tenant;
	}

	public PropertyEntity getProperty() {
		return property;
	}

	public void setProperty(PropertyEntity property) {
		this.property = property;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "UserReviewEntity [tenant=" + tenant + ", property=" + property + ", rating=" + rating + ", comments="
				+ comments + "]";
	}
	
	
}
