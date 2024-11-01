package com.example.demo.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.api.entities.Classe;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    List<Classe> findByNomAndNumSalle(String nom, int numSalle);
    
    @Query("SELECT c FROM Classe c WHERE c.numSalle > :minSalle")
    List<Classe> findClassesWithSalleSuperieure(@Param("minSalle") int minSalle);
    
    @Query("SELECT c FROM Classe c WHERE c.nom LIKE %:searchTerm%")
    Page<Classe> searchByNom(@Param("searchTerm") String searchTerm, Pageable pageable);
}
