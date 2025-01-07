package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cesizen.DTO.UserDTO;
import com.cesizen.model.User;
import com.cesizen.services.UserServices;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserServices userServices;
	
	@GetMapping
    public List<UserDTO> getAllUsers() {
        return userServices.getAllUsers()
                .stream()
                .map(userServices::toDTO) // Transformation User en UserDTO
                .collect(Collectors.toList());
    }
	
	 @PostMapping
	    public UserDTO createUser(@RequestBody UserDTO userDTO) {
		 User user = userServices.toEntity(userDTO);
	        User savedUser = userServices.saveUser(user);

	        return userServices.toDTO(savedUser);
	    }
}
