package com.example.demo.api.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.example.demo.api.entities.dto.ClassDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.api.entities.Classe;

public interface ClasseService {
    Classe saveClasse(Classe classe);
    Optional<Classe> getClasseById(Long id);
    List<ClassDto> getAllClasses();
    public Classe updateClasse(Classe classe);
    void deleteClasse(Long id);

    Page<ClassDto> getAllClassesWithPagination(Pageable pageable);

    List<ClassDto> searchByNumSalle(int numSalle);
}
