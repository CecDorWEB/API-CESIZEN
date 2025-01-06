package com.cesizen.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.UserDTO;
import com.cesizen.model.Role;
import com.cesizen.model.User;
import com.cesizen.repository.RoleRepository;
import com.cesizen.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	LocalDate today = LocalDate.now();
	
	public UserDTO toDTO(User user) {
		return new UserDTO(
				user.getId(),
				user.getFirstname(),
				user.getLastname(),
				user.getPassword(),
				user.getEmail(),
				true,
				Date.valueOf(today)
				);
	}
}