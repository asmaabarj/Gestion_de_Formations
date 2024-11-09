package com.example.demo.api.services.interfaces;

import com.example.demo.api.entities.Apprenant;
import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.dto.ApprenantDto;
import com.example.demo.api.entities.dto.FormateurDto;

import java.util.List;
import java.util.Optional;

public interface ApprenantService {
    Formateur createApprenant(Apprenant apprenant);
    void updateApprenant(Apprenant apprenant);
    void deleteApprenant(Long id);
    List<ApprenantDto> getAllApprenant();
    Optional<Apprenant> getById(Long id);
}
