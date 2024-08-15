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
@Table(name="maintenance_request")
public class MaintenanceRequestEntity extends BaseEntity{
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="property_id",nullable = false)
	private PropertyEntity property;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tenant_id",nullable = false)
	private UserEntity tenant;
	
	@Enumerated(EnumType.STRING)
	@Column(name="issue_type",nullable = false)
	private IssueType issuetype;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status",nullable = false)
	private MaintenanceStatus status;

	public MaintenanceRequestEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaintenanceRequestEntity(PropertyEntity property, UserEntity tenant, IssueType issuetype, String description) {
		super();
		this.property = property;
		this.tenant = tenant;
		this.issuetype = issuetype;
		this.description = description;
		this.status = MaintenanceStatus.PENDING;
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

	public IssueType getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MaintenanceStatus getStatus() {
		return status;
	}

	public void setStatus(MaintenanceStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MaintenanceRequestEntity [property=" + property + ", tenant=" + tenant + ", issuetype=" + issuetype
				+ ", description=" + description + ", status=" + status + "]";
	}
	
	
	
}
