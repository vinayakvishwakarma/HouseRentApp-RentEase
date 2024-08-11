package com.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rent.dto.RentalApplicationDTO;
import com.rent.entities.RentalApplicationEntity;

public interface RentalApplicationRepository extends JpaRepository<RentalApplicationEntity, Long> {

	@Query("select m from RentalApplicationEntity m where m.property.id=:propertyId")
	List<RentalApplicationEntity> findByPropertyId( Long propertyId);
}
