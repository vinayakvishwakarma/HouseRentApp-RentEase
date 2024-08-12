package com.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rent.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

	@Query("SELECT p FROM PaymentEntity p WHERE p.rentalApplication.property.landlord.id = :landlordId")
	List<PaymentEntity> findByLandlordId(@Param("landlordId") Long landlordId);

}
