package com.rent.service;

import java.util.List;

import com.rent.dto.ApiResponse;
import com.rent.entities.PaymentEntity;

public interface PaymentService {

	ApiResponse makePayment(Long rentalAppId,PaymentEntity payment);
	
	List<PaymentEntity> findPaymentByLandlordId(Long landlordId);
	
	ApiResponse approvePayment(Long landlordId,Long paymentId,String status);
}
