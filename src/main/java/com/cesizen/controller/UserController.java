package com.cesizen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.PasswordUpdateDTO;
import com.cesizen.DTO.UserDTO;
import com.cesizen.model.User;
import com.cesizen.repository.UserRepository;
import com.cesizen.services.UserServices;

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
		return userServices.getAllUsers().stream().map(userServices::toDTO) // Transformation User en UserDTO
				.collect(Collectors.toList());
	}

	// S'inscrire
	@PostMapping
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		User user = userServices.toEntity(userDTO);
		User savedUser = userServices.saveUser(user);

		return userServices.toDTO(savedUser);
	}

	// Se connecter
	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody Map<String, String> logInfos) {
		String email = logInfos.get("email");
		String password = logInfos.get("password");

		Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);

		if (optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiant ou mot de passe invalides.");
		}

		User user = optionalUser.get();

		if (user.getStatut() == true) {

			Map<String, Object> userResponse = new HashMap<>();
			userResponse.put("id", user.getId()); // ID de l'utilisateur
			userResponse.put("firstname", user.getFirstname());
			userResponse.put("lastname", user.getLastname());
			userResponse.put("email", user.getEmail()); // Email de l'utilisateur
			userResponse.put("type", user.getRole().getName());
			userResponse.put("role", user.getRole().getId()); // Rôle de l'utilisateur
			userResponse.put("statut", user.getStatut());
			return ResponseEntity.ok(userResponse);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Connexion refusée, compte suspendu !");
		}
	}

	// Supprimer un compte
	@PostMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestBody Map<String, Integer> payload) {
		Integer userId = payload.get("id");

		if (userId == null) {
			return ResponseEntity.badRequest().body("ID manquant");
		}

		boolean isDeleted = userServices.deleteUser(userId);

		if (isDeleted) {
			return ResponseEntity.ok("Utilisateur supprimé avec succès.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
		}
	}

	// Modifier le statut (autorisé/suspendu) d'un utilisateur
	@PostMapping("/autorization")
	public ResponseEntity<String> autorizedUser(@RequestBody Map<String, Integer> payload) {
		Integer userId = payload.get("id");

		if (userId == null) {
			return ResponseEntity.badRequest().body("ID manquant");
		}

		try {
			boolean statutModifie = userServices.autorizedUser(userId);
			if (statutModifie) {
				return ResponseEntity.ok("Autorisation de l'utilisateur modifiée avec succès.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erreur lors de la modification du statut.");
		}
	}
	
	//Modifier le mot de passe
	@PostMapping("/modify")
	public ResponseEntity<String> modifyUserPassword(@RequestBody PasswordUpdateDTO request){
		Integer userId = request.id();
	    String newPassword = request.password();
		
		if (userId == null || newPassword == null || newPassword.isBlank()) {
			return ResponseEntity.badRequest().body("ID ou mot de passe manquant");
		}
		
		try {
			boolean passwordModify=userServices.modifyUserPassword(userId, newPassword);
			if (passwordModify) {
				return ResponseEntity.ok("Mot de passe modifié avec succès.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
			} 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erreur lors de la modification du statut.");
			}
		}
}
