package com.rent.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rent.entities.PropertyEntity;
import com.rent.entities.UserEntity;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {

	@Query("select m from PropertyEntity m where m.landlord.id=:landlordId")
	List<PropertyEntity> getAllPropertyByLandlordId(Long landlordId);
}
