package com.cesizen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ResultDTO;
import com.cesizen.DTO.UserTestResultDTO;
import com.cesizen.model.Result;
import com.cesizen.model.Role;
import com.cesizen.model.UserTestResult;
import com.cesizen.repository.UserTestResultRepository;

@Service
public class UserTestResultServices {
	
	@Autowired
	private UserTestResultRepository userTestResultRepository;

	//Pour renvoyer le résultat conformément au DTO
		public UserTestResultDTO toDTO(UserTestResult userTestResult) {
			return new UserTestResultDTO(userTestResult.getId(), userTestResult.getRealizeDate(), userTestResult.getScore(),
					userTestResult.getTextResult(),userTestResult.getTest().getTitle());
		}
		
	//Récupérer tous les résultats d'un utilisateur
		 public  List<UserTestResult> getAllUserTestResultByUserId(Long userId) {
			 return userTestResultRepository.findAllUserTestResultByUserId(userId);
		    }
		 
	//Sauvegarder un résultat pour un utilisateur
	public UserTestResult saveUserTestResult(UserTestResult userTestResult) {
    	return userTestResultRepository.save(userTestResult);
    }
}
