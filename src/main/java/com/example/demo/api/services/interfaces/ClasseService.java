package com.example.demo.api.services.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.api.entities.Classe;

public interface ClasseService {
    Classe saveClasse(Classe classe);
    Optional<Classe> getClasseById(Long id);
    List<Classe> getAllClasses();
    Page<Classe> getAllClassesWithPagination(Pageable pageable);
    Classe updateClasse(Long id, Classe classe);
    void deleteClasse(Long id);
}
