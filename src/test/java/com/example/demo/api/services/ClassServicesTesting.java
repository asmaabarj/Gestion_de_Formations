package com.example.demo.api.services;
import com.example.demo.api.entities.Classe;
import com.example.demo.api.entities.dto.ClassDto;
import com.example.demo.api.repositories.ClasseRepository;
import com.example.demo.api.services.interfaces.ClasseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
    @SpringBootTest
    @ActiveProfiles("test")
    @Transactional
    public class ClassServicesTesting {

        @Autowired
        private ClasseService classServices;

        @Autowired
        private ClasseRepository classeRepository;

        @Test
        public void createClasse_ShouldSaveAndReturnClasse() {
            Classe classe = new Classe();
            classe.setNom("Test Classe");
            classe.setNumSalle(111);

            Classe result = classServices.saveClasse(classe);

            assertNotNull(result);
            assertNotNull(result.getId());
            assertEquals("Test Classe", result.getNom());
            assertEquals(111, result.getNumSalle());
        }


        @Test
        public void deleteClasse_ShouldRemoveClasse() {
            Classe classe = new Classe();
            classe.setNom("Test Classe");
            classe.setNumSalle(1111);
            Classe savedClasse = classeRepository.save(classe);

            classServices.deleteClasse(savedClasse.getId());

            Optional<Classe> deletedClasse = classeRepository.findById(savedClasse.getId());
            assertFalse(deletedClasse.isPresent());
        }

        @Test
        public void updateClasseDetails() {
            Classe classe = new Classe();
            classe.setNom("Original Classe");
            classe.setNumSalle(202);
            Classe savedClasse = classServices.saveClasse(classe);

            savedClasse.setNom("Updated Classe");
            savedClasse.setNumSalle(303);
            Classe updatedClasse = classServices.updateClasse(savedClasse);

            assertNotNull(updatedClasse);
            assertEquals("Updated Classe", updatedClasse.getNom());
            assertEquals(303, updatedClasse.getNumSalle());
        }

        @Test
        public void getAllClasse_AllClasses() {
            // Create and save a couple of classes
            Classe classe1 = new Classe();
            classe1.setNom("Classe 1");
            classe1.setNumSalle(11);
            classServices.saveClasse(classe1);

            Classe classe2 = new Classe();
            classe2.setNom("Classe 2");
            classe2.setNumSalle(202);
            classServices.saveClasse(classe2);

            List<ClassDto> classes = classServices.getAllClasses();

            assertNotNull(classes);
        }

        @Test
        public void getById_ShouldReturnClasse() {
            Classe classe = new Classe();
            classe.setNom("Test Classe");
            classe.setNumSalle(101);
            Classe savedClasse = classServices.saveClasse(classe);

            Optional<Classe> retrievedClasse = classServices.getClasseById(savedClasse.getId());

            assertTrue(retrievedClasse.isPresent());
            assertEquals("Test Classe", retrievedClasse.get().getNom());
            assertEquals(101, retrievedClasse.get().getNumSalle());
        }
    }

