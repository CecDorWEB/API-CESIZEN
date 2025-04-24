package com.cesizen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.model.Role;
import com.cesizen.model.UserTestResult;
import com.cesizen.repository.UserTestResultRepository;

@Service
public class UserTestResultServices {
	
	@Autowired
	private UserTestResultRepository userTestResultRepository;

	public UserTestResult saveUserTestResult(UserTestResult userTestResult) {
    	return userTestResultRepository.save(userTestResult);
    }
}
