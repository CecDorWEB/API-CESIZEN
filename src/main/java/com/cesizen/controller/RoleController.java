package com.cesizen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesizen.model.Role;
import com.cesizen.services.RoleServices;

@RestController
@RequestMapping("/role")

public class RoleController {
	
	
	private RoleServices roleServices;

	public RoleController(RoleServices roleServices) {
        this.roleServices = roleServices;
    }
  
	 @GetMapping
	    public List<Role> getAllRoles(){
	    	return roleServices.getAllRoles();
	    	}

	 @PostMapping
	 public ResponseEntity<Role> addRole(@RequestBody Role role) {
	     System.out.println("Role received: " + role); // Journal pour voir le contenu de l'objet
	     Role savedRole = roleServices.saveRole(role);
	     return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
	 }
}