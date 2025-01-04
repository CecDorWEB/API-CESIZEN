package com.cesizen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesizen.model.Role;
import com.cesizen.repository.RoleRepository;

@Service
public class RoleServices {
	private RoleRepository roleRepository;
    
   public RoleServices(RoleRepository roleRepository) {
       this.roleRepository = roleRepository;
    }
    
    public List<Role> getAllRoles(){
    	return roleRepository.findAll();
    }
    
    public Role saveRole(Role role) {
    	return roleRepository.save(role);
    }
}