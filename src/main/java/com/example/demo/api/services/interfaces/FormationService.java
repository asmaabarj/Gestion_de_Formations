package com.example.demo.api.services.interfaces;

import com.example.demo.api.entities.Formation;
import com.example.demo.api.entities.dto.FormationDto;

import java.util.List;
import java.util.Optional;

public interface FormationService {

    Formation createFormateur(Formation formation);
    void updateFormateur(Formation formation);
    void deleteFormation(Long id);
    List<FormationDto> getAllFormation();
    Optional<Formation> getById(Long id);
    List<FormationDto> getDetails();
}
