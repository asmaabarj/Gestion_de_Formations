package com.example.demo.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.api.entities.dto.ClassDto;
import com.example.demo.api.exceptions.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.utils.PageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;

import com.example.demo.api.entities.Classe;
import com.example.demo.api.services.interfaces.ClasseService;

@Slf4j
@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor

public class ClasseController {

    private final ClasseService classeService;


    @PostMapping
    public ResponseEntity<Classe> createClasse(@Valid @RequestBody Classe classe) {
        return new ResponseEntity<>(classeService.saveClasse(classe), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtenir toutes les classes")
    @ApiResponse(responseCode = "200", description = "Liste de toutes les classes", content = @Content(schema = @Schema(implementation = ClassDto.class)))
    @GetMapping
    public List<ClassDto> getAllClasses() {
        try {
            List<ClassDto> getAllClass = classeService.getAllClasses();
            return getAllClass.stream().collect(Collectors.toList());
       } catch (ResourceNotFoundException e) {
            log.error("Classe non trouvée avec l'ID: {}", e.getClass());
           throw e;
       }
    }
    @Operation(summary = "Obtenir une classe par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe trouvée", content = @Content(schema = @Schema(implementation = ClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée")
    })
    @GetMapping("/{id}")
    public ClassDto getClasseById(@PathVariable Long id) {
        try {
            return classeService.getClasseById(id)
                    .map(ClassDto::FindById)
                    .orElse(null);
       } catch (ResourceNotFoundException e) {
            log.error("Classe non trouvée avec l'ID: {}", id);
            throw e;
       }
    }



    @Operation(summary = "Mettre à jour une classe existante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe mise à jour avec succès", content = @Content(schema = @Schema(implementation = Classe.class))),
            @ApiResponse(responseCode = "404", description = "Classe non trouvée"),
            @ApiResponse(responseCode = "400", description = "Erreur de validation")
    })
    @PutMapping("/{id}")
    public void updateClasse(@PathVariable Long id, @Valid @RequestBody Classe classes) {

        Optional<Classe> check = classeService.getClasseById(id);
        if (check.isPresent()) {
            classes.setId(id);
            System.out.println(id);
          classeService.updateClasse(classes);
            log.info("Classe mise à jour avec succès : {}");

        } else {
            log.error("Aucune classe trouvée avec le numéro de CLASS: {}", id);
           throw new ResourceNotFoundException("Aucune classe trouvée avec le numéro de CLASS: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtenir toutes les classes paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée de toutes les classes")
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ClassDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ClassDto> pageClasses = classeService.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
                pageClasses.getContent(),
                pageClasses.getNumber(),
                pageClasses.getTotalElements(),
                pageClasses.getTotalPages()
        );
    }

    @Operation(summary = "Rechercher une classe par numéro de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des classes correspondant au numéro de salle", content = @Content(schema = @Schema(implementation = ClassDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClassDto> searchByNumSalle(@PathVariable int numSalle) {
        List<ClassDto> classes = classeService.searchByNumSalle(numSalle);
        if (classes == null || classes.isEmpty()) {
            log.info("Aucune classe trouvée avec le numéro de salle: {}", numSalle);
            throw new ResourceNotFoundException("Aucune classe trouvée avec le numéro de salle: " + numSalle);
        }
        log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, classes.size());
        return classes;
    }
}
