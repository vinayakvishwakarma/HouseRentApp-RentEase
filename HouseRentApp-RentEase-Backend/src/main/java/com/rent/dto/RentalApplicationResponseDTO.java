package com.rent.dto;

import com.rent.entities.RentalStatus;

public class RentalApplicationResponseDTO extends BaseDTO{

	private Long propertyId;
	private Long tenantId;
	private RentalStatus status;
	public RentalApplicationResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalApplicationResponseDTO(Long propertyId, Long tenantId, RentalStatus status) {
		super();
		this.propertyId = propertyId;
		this.tenantId = tenantId;
		this.status = RentalStatus.PENDING;
	}
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public RentalStatus getStatus() {
		return status;
	}
	public void setStatus(RentalStatus status) {
		this.status = status;
	}
	
	
}
