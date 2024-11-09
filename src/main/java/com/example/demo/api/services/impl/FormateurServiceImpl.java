package com.example.demo.api.services.impl;

import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.dto.FormateurDto;
import com.example.demo.api.repositories.FormateurRepository;
import com.example.demo.api.services.interfaces.FormateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class FormateurServiceImpl implements FormateurService {
          private final FormateurRepository formateurRepository;

    @Override
    public Formateur createFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    @Override
    public void updateFormateur(Formateur formateur) {
       if (formateurRepository.existsById(formateur.getId())){
           formateurRepository.save(formateur);
       }
    }

    @Override
    public void deleteFormateur(Long id) {
        if (formateurRepository.existsById(id)){
            formateurRepository.deleteById(id);
        }

        }

    @Override
    public List<FormateurDto> getAllFormateur() {
        List<FormateurDto> formateurDtos=FormateurDto.getAll(formateurRepository.findAll());
        return formateurDtos;
    }


    @Override
    public Optional<Formateur> getById(Long id) {
        return formateurRepository.findById(id);
    }

    public Page<FormateurDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Formateur> formateursPage = formateurRepository.findAllWithPagination(pageable);
        return FormateurDto.getAllWithPagination(formateursPage);
    }

    public List<FormateurDto> searchByNumSalle(String numSalle) {
        List<Formateur> formateurs = formateurRepository.findByClasseNomContaining(numSalle);
        return FormateurDto.getAll(formateurs);
    }

}
