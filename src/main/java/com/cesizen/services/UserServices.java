package com.cesizen.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.UserDTO;
import com.cesizen.model.Administrator;
import com.cesizen.model.Member;
import com.cesizen.model.Role;
import com.cesizen.model.User;
import com.cesizen.repository.RoleRepository;
import com.cesizen.repository.UserRepository;

@Service
public class UserServices {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	LocalDate today = LocalDate.now();

	public UserDTO toDTO(User user) {

		String userType;
		if (user instanceof Member) {
			userType = "MEMBER";
		} else if (user instanceof Administrator) {
			userType = "ADMIN";
		} else {
			throw new IllegalArgumentException("Unknown user type");
		}

		return new UserDTO(user.getId(), userType, user.getFirstname(), user.getLastname(), null, user.getEmail(),
				user.getRole().getId(), user.getStatut(), user.getAdhesionDate());
	}

	public User toEntity(UserDTO userDTO) {
		User user;
		if ("MEMBER".equalsIgnoreCase(userDTO.type())) {
			user = new Member();
		} else if ("ADMIN".equalsIgnoreCase(userDTO.type())) {
			user = new Administrator();
		} else {
			throw new IllegalArgumentException("Invalid user type: " + userDTO.type());
		}

		user.setId(userDTO.id());
		user.setFirstname(userDTO.firstname());
		user.setLastname(userDTO.lastname());
		user.setPassword(userDTO.password());
		user.setEmail(userDTO.email());
		user.setStatut(true);
		user.setAdhesionDate(Date.valueOf(LocalDate.now()));

		// Récupérer le rôle depuis la base de données
		Role role = roleRepository.findById(userDTO.role_id())
				.orElseThrow(() -> new RuntimeException("Role with ID " + userDTO.role_id() + " not found"));
		user.setRole(role);

		return user;
	}

	public boolean deleteUser(Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			userRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	//Modifier le statut du compte (autorisé ou non)
	public boolean autorizedUser(Integer userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			User myUser = user.get();
			myUser.setStatut(!myUser.getStatut());
			userRepository.save(myUser);
			return true;
		} else {
			return false;
		}
	}
	
	//Modifier le mot de passe
	public boolean modifyUserPassword(Integer userId,String newPassword) {
		Optional<User> userDB = userRepository.findById(userId);
		
		if (userDB.isPresent()) {
			User user = userDB.get();
			user.setPassword(newPassword);	
			userRepository.save(user);
			return true;
		} else {
			return false;
		}
	}
}