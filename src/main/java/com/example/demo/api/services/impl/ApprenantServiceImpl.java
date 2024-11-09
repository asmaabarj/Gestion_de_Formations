package com.example.demo.api.services.impl;

import com.example.demo.api.entities.Apprenant;
import com.example.demo.api.entities.dto.ApprenantDto;
import com.example.demo.api.repositories.ApprenantRepository;
import com.example.demo.api.services.interfaces.ApprenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class ApprenantServiceImpl implements ApprenantService{

    private final ApprenantRepository apprenantRepository;


    @Override
    public Apprenant createApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    @Override
    public void updateApprenant(Apprenant apprenant) {
        if (apprenantRepository.existsById(apprenant.getId())){
            apprenantRepository.save(apprenant);
        }

    }

    @Override
    public void deleteApprenant(Long id) {
        if (apprenantRepository.existsById(id)){
            apprenantRepository.deleteById(id);
        }
    }

    @Override
    public List<ApprenantDto> getAllApprenant() {
        List<ApprenantDto> apprenantDtos=ApprenantDto.getAll(apprenantRepository.findAll());
        return apprenantDtos;
    }

    @Override
    public Optional<Apprenant> getById(Long id) {
        return apprenantRepository.findById(id);
    }

    public Page<ApprenantDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Apprenant> apprenantsPage = apprenantRepository.findAllWithPagination(pageable);
        return ApprenantDto.getAllWithPagination(apprenantsPage);
    }

    public List<ApprenantDto> searchByNumSalle(String numSalle) {
        List<Apprenant> apprenants = apprenantRepository.findByClasseNomContaining(numSalle);
        return ApprenantDto.getAll(apprenants);
    }
}
