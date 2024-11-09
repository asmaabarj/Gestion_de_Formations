package com.example.demo.api.services.interfaces;

import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.dto.FormateurDto;

import java.util.List;
import java.util.Optional;

public interface FormateurService {
    Formateur createFormateur(Formateur formateur);
    void updateFormateur(Formateur formateur);
    void deleteFormateur(Long id);
    List<FormateurDto> getAllFormateur();
    Optional<Formateur> getById(Long id);

}
