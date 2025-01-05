package com.cesizen.controller;

import java.util.ArrayList;
import java.util.List;

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
	 
	/*
	public UserController(UserServices userServices) {
		this.userServices=userServices;
	}
	*/

	@GetMapping
	public List<UserDTO> list() {
        List<User> list = userServices.getAllUsers();
        List<UserDTO> listDTO = new ArrayList<UserDTO>();
        for (User u : list) {
            listDTO.add(new UserDTO(u));
        }
        return listDTO;
    }
	
	@PostMapping
	public User user(@RequestBody User user) {
        UserDTO userDTO = new UserDTO(userServices.saveUser(user));
        return userServices.saveUser(user);
    }

}
