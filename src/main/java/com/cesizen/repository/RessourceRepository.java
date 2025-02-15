package com.cesizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.Ressource;

@Repository
public interface RessourceRepository extends JpaRepository<Ressource, Long> {

}
