package com.cesizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}