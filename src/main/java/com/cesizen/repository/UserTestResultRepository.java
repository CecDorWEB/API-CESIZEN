package com.cesizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.UserTestResult;

@Repository
public interface UserTestResultRepository extends JpaRepository<UserTestResult, Long> {

}
