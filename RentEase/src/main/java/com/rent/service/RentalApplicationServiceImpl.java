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
	public ApiResponse createRentalApplication( RentalApplicationDTO dto) {
		
		PropertyEntity property=propertyRepository.findById(dto.getPropertyId())
				.orElseThrow(()->new InvalidCredentialsException("Invalid property!"));
		
		UserEntity user=userRepository.findById(dto.getTenantId())
				.orElseThrow(()->new InvalidCredentialsException("invalid id!"));
		if(user.getRole().equals(Role.TENANT)) {
			/*
			 * PropertyEntity property=propertyRepository.findById(propertyId)
			 * .orElseThrow(()->new InvalidCredentialsException("Invalid property!"));
			 */
//			rentalApp.setTenant(user);
//			rentalApp.setProperty(property);
//			rentalApplicationRepository.save(rentalApp);
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

//	@Override
//	public List<RentalApplicationResponseDTO> getAllRentalApplicationByPropertyId(Long propertyId) {
//		PropertyEntity property=propertyRepository.findById(propertyId)
//				.orElseThrow(()->new InvalidCredentialsException("invalid property id!"));
//		List<RentalApplicationResponseDTO> dto=rentalApplicationRepository.findByPropertyId(propertyId)
//				.stream()
//				.map(rental->modelMapper.map(rental, RentalApplicationResponseDTO.class))
//				.collect(Collectors.toList());
////		for (RentalApplicationResponseDTO d : dto) {
////			d.setPropertyId(rentalApplicationRepository.findById(d.getId())
////					.orElseThrow(()->new InvalidCredentialsException("invalid property id!"))
////					.getProperty().getId());
////			d.setTenantId(rentalApplicationRepository.findById(d.getId())
////					.orElseThrow(()->new InvalidCredentialsException("invalid property id!"))
////					.getTenant().getId());
////			dto.add(d);
////		}
//		
//		return dto;
//	}
	
	
	
}
