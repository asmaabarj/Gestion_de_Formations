package com.example.demo.api.services;

import com.example.demo.api.entities.dto.ClassDto;
import com.example.demo.api.entities.Classe;
import com.example.demo.api.repositories.ClasseRepository;
import com.example.demo.api.services.impl.ClasseServiceImpl;
import com.example.demo.api.services.interfaces.ClasseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TestClasseTest {
    @Mock
    private ClasseRepository classeRepository;

    @InjectMocks
    private ClasseServiceImpl classServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClasse() {
        Classe classe = new Classe();
        classe.setNom("New Classe");

        when(classeRepository.save(any(Classe.class))).thenReturn(classe);

        Classe createdClasse = classServices.saveClasse(classe);

        assertNotNull(createdClasse);
        assertEquals("New Classe", createdClasse.getNom());

        verify(classeRepository).save(classe);
    }

    @Test
    public void testUpdateClasse() {
        Classe classe = new Classe();
        classe.setNom("Updated Classe");

        when(classeRepository.existsById(classe.getId())).thenReturn(true);
        when(classeRepository.save(any(Classe.class))).thenReturn(classe);

        Classe updatedClasse = classServices.updateClasse(classe);

        assertNotNull(updatedClasse);
        assertEquals("Updated Classe", updatedClasse.getNom());

        verify(classeRepository).existsById(classe.getId());
        verify(classeRepository).save(classe);
    }

    @Test
    public void testDeleteClasse() {
        Long id = 1L;

        when(classeRepository.existsById(id)).thenReturn(true);
        doNothing().when(classeRepository).deleteById(id);

        classServices.deleteClasse(id);


        verify(classeRepository).deleteById(id);
    }

    @Test
    public void testGetAllClasse() {
        Classe classe1 = new Classe();
        Classe classe2 = new Classe();
        when(classeRepository.findAll()).thenReturn(Arrays.asList(classe1, classe2));

        List<ClassDto> classDtos = classServices.getAllClasses();

        assertNotNull(classDtos);
        assertEquals(2, classDtos.size());

        verify(classeRepository).findAll();
    }

    @Test
    public void testGetAllClassesWithPagination() {
        Pageable pageable = mock(Pageable.class);
        Classe classe = new Classe();
        Page<Classe> classePage = new PageImpl<>(Arrays.asList(classe));
        when(classeRepository.findAllWithPagination(pageable)).thenReturn(classePage);

        Page<ClassDto> classDtoPage = classServices.getAllClassesWithPagination(pageable);

        assertNotNull(classDtoPage);
        assertEquals(1, classDtoPage.getTotalElements());

        verify(classeRepository).findAllWithPagination(pageable);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Classe classe = new Classe();
        when(classeRepository.findById(id)).thenReturn(Optional.of(classe));

        Optional<Classe> foundClasse = classServices.getClasseById(id);

        assertTrue(foundClasse.isPresent());

        verify(classeRepository).findById(id);
    }

    @Test
    public void testSearchByNumSalle() {
        int numSalle =101;
        Classe classe = new Classe();
        when(classeRepository.findByNumSalleContaining(numSalle)).thenReturn(Arrays.asList(classe));

        List<ClassDto> classDtos = classServices.searchByNumSalle(numSalle);

        assertNotNull(classDtos);
        assertEquals(1, classDtos.size());

        verify(classeRepository).findByNumSalleContaining(numSalle);
    }
}