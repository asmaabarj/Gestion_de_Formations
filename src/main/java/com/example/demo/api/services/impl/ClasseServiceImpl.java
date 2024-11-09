package com.example.demo.api.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.example.demo.api.entities.dto.ClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.api.entities.Classe;
import com.example.demo.api.repositories.ClasseRepository;
import com.example.demo.api.services.interfaces.ClasseService;
import com.example.demo.api.utils.LoggerMessage;

@Service
@RequiredArgsConstructor

public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;


    @Override
    public Classe saveClasse(Classe classe) {
        LoggerMessage.info("Sauvegarde d'une nouvelle classe: " + classe.getNom());
        return classeRepository.save(classe);
    }

    @Override
    public Optional<Classe> getClasseById(Long id) {
        LoggerMessage.info("Recherche de la classe avec l'ID: " + id);
        return classeRepository.findById(id);
    }

    @Override
    public List<ClassDto> getAllClasses() {
        LoggerMessage.info("Récupération de toutes les classes");
        List<ClassDto> classDtos = ClassDto.getAll(classeRepository.findAll());

        return classDtos ;
    }


    public List<ClassDto> searchByNumSalle(int numSalle) {
        List<Classe> classes = classeRepository.findByNumSalleContaining(numSalle);
        return ClassDto.getAll(classes);
    }
    public Page<ClassDto> getAllClassesWithPagination(Pageable pageable) {
        Page<Classe> classePage = classeRepository.findAllWithPagination(pageable);
        return ClassDto.getAllWithPagination(classePage);
    }

    @Override
    public Classe updateClasse(Classe classe) {
        if(classeRepository.existsById(classe.getId())){
            System.out.println(classe);

               return  classeRepository.save(classe);


          //  return  classeRepository.save(classe);
        }
        return null;
    }

    @Override
    public void deleteClasse(Long id) {
        LoggerMessage.info("Suppression de la classe avec l'ID: " + id);
        classeRepository.deleteById(id);
    }
}
