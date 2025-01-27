package com.cesizen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	 Optional<User> findByEmailAndPassword(String email, String password);
}
