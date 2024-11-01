package com.example.demo.api.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.api.entities.Classe;
import com.example.demo.api.repositories.ClasseRepository;
import com.example.demo.api.services.interfaces.ClasseService;
import com.example.demo.api.utils.LoggerMessage;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    @Autowired
    public ClasseServiceImpl(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

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
    public List<Classe> getAllClasses() {
        LoggerMessage.info("Récupération de toutes les classes");
        return classeRepository.findAll();
    }

    @Override
    public Page<Classe> getAllClassesWithPagination(Pageable pageable) {
        LoggerMessage.info("Récupération des classes avec pagination");
        return classeRepository.findAll(pageable);
    }

    @Override
    public Classe updateClasse(Long id, Classe classe) {
        LoggerMessage.info("Mise à jour de la classe avec l'ID: " + id);
        return classeRepository.findById(id)
            .map(existingClasse -> {
                existingClasse.setNom(classe.getNom());
                existingClasse.setNumSalle(classe.getNumSalle());
                return classeRepository.save(existingClasse);
            })
            .orElseThrow(() -> new EntityNotFoundException("Classe non trouvée avec l'ID: " + id));
    }

    @Override
    public void deleteClasse(Long id) {
        LoggerMessage.info("Suppression de la classe avec l'ID: " + id);
        classeRepository.deleteById(id);
    }
}
