package com.rent.service;

import com.rent.dto.ApiResponse;
import com.rent.dto.AuthDTO;
import com.rent.dto.RegDTO;
import com.rent.dto.UserResponseDTO;
import java.util.List;


public interface UserService {
	
	ApiResponse signUp(RegDTO dto);
	
	UserResponseDTO signIn(AuthDTO dto);
	
	List<UserResponseDTO> getAllUsers();
	
	ApiResponse updateUserDetails(Long id,RegDTO dto);
	
	UserResponseDTO getUser(Long id);
	
}
