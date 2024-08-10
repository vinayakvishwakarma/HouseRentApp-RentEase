package com.rent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rent.entities.Role;
import com.rent.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String email,String pass);
	
	@Query("select m from UserEntity m where m.role=LANDLORD")
	Optional<UserEntity> findByRole(Role s);
}
