package com.example.demo.api.services.impl;


import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.Formation;
import com.example.demo.api.entities.dto.FormationDto;
import com.example.demo.api.repositories.FormationRepository;
import com.example.demo.api.services.interfaces.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;

    @Override
    public Formation createFormateur(Formation formation) {

        return formationRepository.save(formation);
    }

    @Override
    public void updateFormateur(Formation formation) {
        if(formationRepository.existsById(formation.getId())){
            formationRepository.save(formation);
        }
    }

    @Override
    public void deleteFormation(Long id) {
        if(formationRepository.existsById(id)){
            formationRepository.deleteById(id);
        }
    }

    @Override
    public List<FormationDto> getAllFormation() {
        return    FormationDto.formateurDtos(formationRepository.findAll());

    }


    @Override
    public Optional<Formation> getById(Long id) {
        return formationRepository.findById(id);
    }

    @Override
    public List<FormationDto> getDetails() {
        List<Formation> formations = formationRepository.findAll();
        List<Formation> detailedFormations = formationRepository.findAllWithDetails(formations);
        return FormationDto.formateurDtos(detailedFormations);
    }

    public Page<FormationDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Formation> formationPage = formationRepository.findAllWithPagination(pageable);
        return FormationDto.getAllWithPagination(formationPage);
    }

    public List<FormationDto> searchByName(String nom) {
        List<Formation> formations = formationRepository.findByNomContaining(nom);
        return FormationDto.formateurDtos(formations);
    }
}
