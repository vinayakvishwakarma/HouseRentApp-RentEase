package com.rent.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dto.ApiResponse;
import com.rent.dto.AuthDTO;
import com.rent.dto.RegDTO;
import com.rent.dto.UserResponseDTO;
import com.rent.entities.PropertyEntity;
import com.rent.entities.UserEntity;
import com.rent.exception.InvalidCredentialsException;
import com.rent.repository.PropertyRepository;
import com.rent.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public ApiResponse signUp(RegDTO dto) {
		UserEntity user=modelMapper.map(dto, UserEntity.class);
		userRepository.save(user);
		return new ApiResponse("User Registered!");
		
	}


	@Override
	public UserResponseDTO signIn(AuthDTO dto) {
		UserEntity user=userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(()->new InvalidCredentialsException("Invalid Credentials!"));
		
		return modelMapper.map(user, UserResponseDTO.class);
	}


	@Override
	public List<UserResponseDTO> getAllUsers() {
		
		return userRepository.findAll()
				.stream()
				.map(user->modelMapper.map(user, UserResponseDTO.class))
				.collect(Collectors.toList());
	}


	@Override
	public ApiResponse updateUserDetails(Long id,RegDTO dto) {
		/*
		 * UserEntity user=modelMapper.map(dto, UserEntity.class);
		 * userRepository.save(user);
		 */
		UserEntity user=userRepository.findById(id)
				.orElseThrow(()->new InvalidCredentialsException("invalid id!"));
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setGender(dto.getGender());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setRole(dto.getRole());
		
		userRepository.save(user);
		
		return new ApiResponse("User Updated Successfully!");
	}


	@Override
	public UserResponseDTO getUser(Long id) {
		UserEntity user=userRepository.findById(id)
				.orElseThrow(()-> new InvalidCredentialsException("Invalid user id!"));
		return modelMapper.map(user, UserResponseDTO.class);
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
	
	

}
