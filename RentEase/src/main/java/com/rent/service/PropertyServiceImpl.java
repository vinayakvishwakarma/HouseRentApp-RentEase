package com.rent.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dto.ApiResponse;
import com.rent.entities.PropertyEntity;
import com.rent.entities.Role;
import com.rent.entities.UserEntity;
import com.rent.exception.InvalidCredentialsException;
import com.rent.repository.PropertyRepository;
import com.rent.repository.UserRepository;

@Service
@Transactional
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addProperty(Long landlordId, PropertyEntity property) {
		UserEntity user=userRepository.findById(landlordId)
				.orElseThrow(()->new InvalidCredentialsException("invalid id!"));
		//user.getRole();
		if(user.getRole().equals(Role.LANDLORD)) {
			property.setLandlord(user);
			propertyRepository.save(property);
		}
		else {
			throw new InvalidCredentialsException("invalid id!");
		}
		return new ApiResponse("property added!");
	}

	@Override
	public List<PropertyEntity> getAllProperty() {
		return propertyRepository.findAll();
		
	}

	@Override
	public PropertyEntity getPropertyById(Long propertyId) {
		
		 return propertyRepository.findById(propertyId)
		  .orElseThrow(()->new InvalidCredentialsException("invalid id!"));
		 
		
		
	}

	@Override
	public ApiResponse deleteProperty(Long landlordId, Long propertyId) {
		PropertyEntity property=propertyRepository.findById(propertyId)
				.orElseThrow(()->new InvalidCredentialsException("invalid property id!"));
		if(property.getLandlord().getId().equals(landlordId)) {
			property.setLandlord(null);
			propertyRepository.save(property);
			//propertyRepository.deleteById(propertyId);
			propertyRepository.delete(property);
			//propertyRepository.deleteById(propertyId);
		}
		else {
			throw new IllegalArgumentException("User is not the owner of the property!");
		}
		
		return new ApiResponse("Property deleted!");
	}

	@Override
	public List<PropertyEntity> getAllPropertyByLandlordId(Long landlordId) {
		
		return propertyRepository.getAllPropertyByLandlordId(landlordId);
	}

	
	
}
