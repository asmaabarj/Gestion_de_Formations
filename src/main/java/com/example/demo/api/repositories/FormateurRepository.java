package com.example.demo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.api.entities.Formateur;

@Repository
public interface FormateurRepository extends JpaRepository<Formateur,Long> {
}
