package com.rent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rent.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmailAndPassword(String email,String pass);
}
