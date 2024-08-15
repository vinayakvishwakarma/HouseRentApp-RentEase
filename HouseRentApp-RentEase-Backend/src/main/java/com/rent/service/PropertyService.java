package com.rent.service;

import java.util.List;

import com.rent.dto.ApiResponse;
import com.rent.entities.PropertyEntity;
import com.rent.entities.UserEntity;

public interface PropertyService {

	ApiResponse addProperty(Long landlordId,PropertyEntity property);
	
	List<PropertyEntity> getAllProperty();
	
	List<PropertyEntity> getAllPropertyByLandlordId(Long landlordId);
	
	PropertyEntity getPropertyById(Long propertyId);
	
	ApiResponse deleteProperty(Long landlordId,Long propertyId);
	
	
}
