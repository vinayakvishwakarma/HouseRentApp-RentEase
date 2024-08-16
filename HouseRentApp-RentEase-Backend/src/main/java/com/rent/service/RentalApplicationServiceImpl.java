package com.rent.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.rent.dto.ApiResponse;
import com.rent.dto.RentalApplicationDTO;
import com.rent.dto.RentalApplicationResponseDTO;
import com.rent.entities.PropertyEntity;
import com.rent.entities.RentalApplicationEntity;
import com.rent.entities.RentalStatus;
import com.rent.entities.Role;
import com.rent.entities.UserEntity;
import com.rent.exception.InvalidCredentialsException;
import com.rent.repository.PropertyRepository;
import com.rent.repository.RentalApplicationRepository;
import com.rent.repository.UserRepository;

@Service
@Transactional
public class RentalApplicationServiceImpl implements RentalApplicationService {

	@Autowired
	private RentalApplicationRepository rentalApplicationRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse createRentalApplication(Long tenantId,Long propertyId, RentalApplicationDTO dto) {
		
		PropertyEntity property=propertyRepository.findById(propertyId)
				.orElseThrow(()->new InvalidCredentialsException("Invalid property!"));
		
		UserEntity user=userRepository.findById(tenantId)
				.orElseThrow(()->new InvalidCredentialsException("invalid id!"));
		if(user.getRole().equals(Role.TENANT)) {
			
			RentalApplicationEntity rentalApp=modelMapper.map(dto, RentalApplicationEntity.class);
			rentalApp.setTenant(user);
			rentalApp.setProperty(property);
			rentalApplicationRepository.save(rentalApp);
			
		}
		else {
			throw new InvalidCredentialsException("invalid tenant id!");
		}
		return new ApiResponse("Rental Application submitted!");
	}

	@Override
	public List<RentalApplicationEntity> getAllRentalApplicationByPropertyId(Long propertyId) {
		
		return rentalApplicationRepository.findByPropertyId(propertyId);
	}

	@Override
	public ApiResponse approveRentalApplication(Long landlordId, Long rentalId, String status) {
		RentalApplicationEntity rentalApp=rentalApplicationRepository.findById(rentalId)
				.orElseThrow(()->new InvalidCredentialsException("invalid rentalApp id!"));
		PropertyEntity property=rentalApp.getProperty();
		if(property.getLandlord().getId().equals(landlordId) && property.getLandlord().getRole().equals(Role.LANDLORD) ) {
			RentalStatus st=RentalStatus.valueOf(status.toUpperCase());
			rentalApp.setStatus(st);
			rentalApplicationRepository.save(rentalApp);
		}
		else {
			throw new InvalidCredentialsException("invalid landlord id !");
		}
		return new ApiResponse("updated Rental Application!!");
	}

	@Override
	public List<RentalApplicationEntity> getAllRentalApplicationByTenantId(Long tenantId) {
		return rentalApplicationRepository.findByTenantId(tenantId);
		
	}


	
	
	
}
