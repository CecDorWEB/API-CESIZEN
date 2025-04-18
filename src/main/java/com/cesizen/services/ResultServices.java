package com.cesizen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesizen.DTO.ResultDTO;
import com.cesizen.model.Result;
import com.cesizen.repository.ResultRepository;

@Service
public class ResultServices {

	@Autowired
	private ResultRepository resultRepository;
	
	 public ResultDTO findResultByScore(Long ressourceId, Integer score) {
	        Result result = resultRepository.findResultTestByScore(ressourceId, score);
	        if (result != null) {
	            return new ResultDTO(result.getTitle(), result.getContent());
	        } else {
	            return null;
	        }
	    }
}