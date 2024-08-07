package com.rent.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.dto.ApiResponse;
import com.rent.dto.AuthDTO;
import com.rent.dto.RegDTO;
import com.rent.dto.UserResponseDTO;
import com.rent.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> userSignUp(@RequestBody RegDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(dto));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			
		}
	}
	
	@PostMapping("/signIn")
	public ResponseEntity<?> userSignIn(@RequestBody AuthDTO dto){
		try {
			UserResponseDTO responseDto=userService.signIn(dto);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping
	public ResponseEntity<?> listAllUsers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody RegDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserDetails(dto));
		}
		catch(RuntimeException e){
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserDetails(@PathVariable @Valid Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
	
}
