package com.example.demo.api.repositories;


import com.example.demo.api.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository<Formation , Long> {

    }
