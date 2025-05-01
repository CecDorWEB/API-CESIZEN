package com.cesizen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cesizen.model.UserTestResult;

@Repository
public interface UserTestResultRepository extends JpaRepository<UserTestResult, Long> {

	@Query("SELECT utr FROM UserTestResult utr WHERE utr.user.id = :userId order by utr.realizeDate desc")
	List<UserTestResult> findAllUserTestResultByUserId(@Param("userId") Long userId);

}
