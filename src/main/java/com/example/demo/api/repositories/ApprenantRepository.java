package com.example.demo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.api.entities.Apprenant;

@Repository
public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {

}
