package com.rent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.dto.ApiResponse;
import com.rent.dto.RentalApplicationDTO;
import com.rent.entities.RentalApplicationEntity;
import com.rent.service.RentalApplicationService;

@RestController
@RequestMapping("/rentalApplication")
@CrossOrigin(origins="http://localhost:3000")
public class RentalApplicationController {

	@Autowired
	private RentalApplicationService rentalApplicationService;
	
	@PostMapping
	ResponseEntity<?> createRentalApplication(@RequestBody RentalApplicationDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rentalApplicationService.createRentalApplication( dto));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/{propertyId}")
	public ResponseEntity<?> getAllRentalApplicationByPropertyId(@PathVariable Long propertyId){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rentalApplicationService.getAllRentalApplicationByPropertyId(propertyId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/{rentalId}")
	public ResponseEntity<?> approveRentalApplication(Long landlordId, Long rentalId, String status){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rentalApplicationService.approveRentalApplication(landlordId, rentalId, status));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/tenantId")
	public ResponseEntity<?> getAllRentalApplicationByTenantId(@PathVariable Long tenantId){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(rentalApplicationService.getAllRentalApplicationByTenantId(tenantId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	
}
