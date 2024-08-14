package com.rent.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rent.dto.ApiResponse;
import com.rent.entities.PaymentEntity;
import com.rent.entities.PaymentStatus;
import com.rent.entities.RentalApplicationEntity;
import com.rent.entities.Role;
import com.rent.exception.InvalidCredentialsException;
import com.rent.repository.PaymentRepository;
import com.rent.repository.PropertyRepository;
import com.rent.repository.RentalApplicationRepository;
import com.rent.repository.UserRepository;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RentalApplicationRepository rentalApplicationRepository;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public ApiResponse makePayment(Long rentalAppId, PaymentEntity payment) {
		RentalApplicationEntity rentalApp=rentalApplicationRepository.findById(rentalAppId)
				.orElseThrow(()->new InvalidCredentialsException("invalid Rental Application id!"));
		if(rentalApp.getTenant().getRole().equals(Role.TENANT)) {
			payment.setRentalApplication(rentalApp);
			paymentRepository.save(payment);
		}
		else {
			throw new InvalidCredentialsException("Invalid tenant!");
		}
		return new ApiResponse("Payment successful!");
	}


	@Override
	public ApiResponse approvePayment(Long landlordId, Long paymentId, String status) {
		PaymentEntity payment=paymentRepository.findById(paymentId)
				.orElseThrow(()->new InvalidCredentialsException("invalid payment id!"));
		PaymentStatus st= PaymentStatus.valueOf(status.toUpperCase());
		payment.setStatus(st);
		paymentRepository.save(payment);
		return new ApiResponse("payment recieved!");
	}


	@Override
	public List<PaymentEntity> findPaymentByLandlordId(Long landlordId) {
		
		return paymentRepository.findByLandlordId(landlordId);
	}
	
	
	
}
