package com.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.dto.ApiResponse;
import com.rent.entities.PropertyEntity;
import com.rent.entities.UserEntity;
import com.rent.service.PropertyService;

@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> addProperty(@PathVariable Long id, @RequestBody PropertyEntity property){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addProperty(id, property));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getAllProperty(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(propertyService.getAllProperty());
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<?> getPropertyById(@PathVariable Long propertyId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(propertyService.getPropertyById(propertyId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	
	@DeleteMapping("/{landlordId}/{propertyId}")
	public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId,@PathVariable Long landlordId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(propertyService.deleteProperty(landlordId, propertyId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
		
	}
	
	@GetMapping("/prop/{landlordId}")
	public ResponseEntity<?> getAllPropertyByLandlordId(@PathVariable Long landlordId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(propertyService.getAllPropertyByLandlordId(landlordId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
}
