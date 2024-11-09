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
    @Query("SELECT c FROM Classe c")
    Page<Classe> findAllWithPagination(Pageable pageable);

    @Query("SELECT c FROM Classe c WHERE CAST(c.numSalle AS string) LIKE CONCAT('%', :numSalle, '%')")
    List<Classe> findByNumSalleContaining(@Param("numSalle") int numSalle);

}
