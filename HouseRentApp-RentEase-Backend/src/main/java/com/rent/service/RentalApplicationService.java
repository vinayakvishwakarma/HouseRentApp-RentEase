package com.rent.service;

import java.util.List;

import com.rent.dto.ApiResponse;
import com.rent.dto.RentalApplicationDTO;
import com.rent.dto.RentalApplicationResponseDTO;
import com.rent.entities.RentalApplicationEntity;

public interface RentalApplicationService {

	ApiResponse createRentalApplication(Long tenantId,Long propertyId, RentalApplicationDTO dto);
	
	//List<RentalApplicationResponseDTO> getAllRentalApplicationByPropertyId(Long propertyId);
	
	List<RentalApplicationEntity> getAllRentalApplicationByPropertyId(Long propertyId);
	
	ApiResponse approveRentalApplication(Long landlordId,Long rentalId,String status);
	
	List<RentalApplicationEntity> getAllRentalApplicationByTenantId(Long tenantId);
}
