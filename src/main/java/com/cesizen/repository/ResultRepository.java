package com.cesizen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesizen.model.Paragraph;
import com.cesizen.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

	@Query("SELECT resu FROM Result resu WHERE resu.test.id = :ressourceId")
	List<Result> findAllResultByRessourceId(@Param("ressourceId") Long ressourceId);

	
	 @Query("SELECT trt FROM Result trt WHERE trt.test.id = :ressourceId " +
	           "AND :score >= trt.minScore AND :score < trt.maxScore")
	    Result findResultTestByScore(@Param("ressourceId") Long ressourceId, @Param("score") Integer score);
	 
}