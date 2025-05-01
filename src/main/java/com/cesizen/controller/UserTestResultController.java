package com.cesizen.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cesizen.DTO.ResultDTO;
import com.cesizen.DTO.UserTestResultDTO;
import com.cesizen.model.UserTestResult;
import com.cesizen.services.UserTestResultServices;

@RestController
@RequestMapping("/userResults")
public class UserTestResultController {

	@Autowired
	private UserTestResultServices userTestResultService;
	
	
	//Récupérer le résultat de l'utilisateur connecté
	@GetMapping
	public List<UserTestResultDTO> getUserTestResultByUserId(@RequestParam Long userId) {
	    return userTestResultService.getAllUserTestResultByUserId(userId).stream().map(userTestResultService::toDTO)
				.collect(Collectors.toList());
	}
	
	// Ajouter ou modifier un résultat
	@PostMapping("/save")
	public ResponseEntity<UserTestResult> addUserTestResult(
			@RequestBody UserTestResult userTestResult) {
		
		UserTestResult savedUserTestResult = userTestResultService.saveUserTestResult(userTestResult);
	     return new ResponseEntity<>(savedUserTestResult, HttpStatus.CREATED);
	}
	
	
}
