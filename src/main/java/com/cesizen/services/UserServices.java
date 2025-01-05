package com.cesizen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.model.User;
import com.cesizen.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
}