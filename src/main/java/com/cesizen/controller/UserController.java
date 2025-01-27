package com.cesizen.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.cesizen.DTO.UserDTO;
import com.cesizen.model.User;
import com.cesizen.services.UserServices;
import com.cesizen.repository.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
    public List<UserDTO> getAllUsers() {
        return userServices.getAllUsers()
                .stream()
                .map(userServices::toDTO) // Transformation User en UserDTO
                .collect(Collectors.toList());
    }
	
	@GetMapping("/login")
	public ResponseEntity<String> userLogin(@RequestParam String email, @RequestParam String password){
		
		   System.out.println("Email reçu : " + email);
		    System.out.println("Mot de passe reçu : " + password);
		    
		Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
		
		if(optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiant ou mot de passe invalides.");
		}
		
			User user = optionalUser.get();
			
			if (user.getStatut() == true) {
				return ResponseEntity.ok("Connexion réussie !");
				} else {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Connexion refusée, compte suspendu !");
					} 
			}
	
	 @PostMapping
	    public UserDTO createUser(@RequestBody UserDTO userDTO) {
		 User user = userServices.toEntity(userDTO);
	        User savedUser = userServices.saveUser(user);

	        return userServices.toDTO(savedUser);
	    }
}
