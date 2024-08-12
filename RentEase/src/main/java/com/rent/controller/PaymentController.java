package com.rent.controller;

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
import com.rent.entities.PaymentEntity;
import com.rent.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/{rentalAppId}")
	public ResponseEntity<?>  makePayment(@PathVariable Long rentalAppId, @RequestBody PaymentEntity payment){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.makePayment(rentalAppId, payment));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			
		}
		
	}
	@GetMapping("/{landlordId}")
	public ResponseEntity<?> findPaymentByLandlordId(@PathVariable Long landlordId){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.findPaymentByLandlordId(landlordId));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			
		}
	}
	
	@PutMapping("/{paymentId}")
	public ResponseEntity<?> approvePayment(  Long landlordId, Long paymentId, String status){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.approvePayment(landlordId, paymentId, status));
		}
		catch(RuntimeException e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
			
		}
	}

}
