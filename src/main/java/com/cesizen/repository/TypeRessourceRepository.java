package com.cesizen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cesizen.model.TypeRessource;

@Repository
public interface TypeRessourceRepository extends JpaRepository<TypeRessource, Long> {

}
