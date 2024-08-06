package com.rent.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.rent.dto.ApiResponse;
import com.rent.dto.AuthDTO;
import com.rent.dto.RegDTO;
import com.rent.dto.UserResponseDTO;
import com.rent.entities.UserEntity;
import com.rent.exception.InvalidCredentialsException;
import com.rent.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	

}
