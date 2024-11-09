package com.example.demo.api.controllers;
import com.example.demo.api.entities.Apprenant;
import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.dto.ApprenantDto;
import com.example.demo.api.entities.dto.FormateurDto;
import com.example.demo.api.exceptions.ResourceNotFoundException;
import com.example.demo.api.exceptions.SuccesRequestException;
import com.example.demo.api.services.impl.ApprenantServiceImpl;
import com.example.demo.api.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/apprenants")
@RequiredArgsConstructor
public class ApprenantController {

    private final ApprenantServiceImpl apprenantService;

    @PostMapping
    public Apprenant createApprenant(@Valid @RequestBody Apprenant apprenant){
        log.info("Création d'une nouvelle apprenant : {}", apprenant);
        Apprenant createApprenant = apprenantService.createApprenant(apprenant);
        log.info("Formateur créée avec succès : {}", createApprenant);
        return createApprenant;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateApprenant(@PathVariable Long id, @Valid @RequestBody Apprenant apprenant) {
        try {
        Optional<Apprenant> check = apprenantService.getById(id);
        if (check.isPresent()) {
            apprenant.setId(id);
               log.info("Mise à jour du apprenant avec l'ID : {}", id);
            apprenantService.updateApprenant(apprenant);
               throw new SuccesRequestException("apprenant update avec succès : " + id);

           } else {
                throw new ResourceNotFoundException("Aucun apprenant trouvé avec l'ID : " + id);
           }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erreur lors de la mise à jour : corps de la requête manquant ou invalide");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteApprenant(@PathVariable Long id) {
        Optional<Apprenant> check= apprenantService.getById(id);
        if (check.isPresent()){
            apprenantService.deleteApprenant(id);
            log.info("Apprenant supprimer avec succès : {}", id);
            throw new SuccesRequestException("Apprenant Supprimer avec succès : " + id);

        }else {
            throw new ResourceNotFoundException("Aucune Apprenant trouvée avec le numéro : " + id);

        }

    }
    @GetMapping
    public List<ApprenantDto> getAllApprenant(){

        return  apprenantService.getAllApprenant();


    }

    @GetMapping("/{id}")
    public ApprenantDto getApprenantById(@PathVariable Long id) {
         try {
        return apprenantService.getById(id)
                .map(ApprenantDto::findById)
                .orElse(null);
         } catch (ResourceNotFoundException e) {
            log.error("Apprenant non trouvée avec l'ID: {}", id);
             throw e;
         }
    }

    @Operation(summary = "Rechercher un apprenant par numSalle de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des Apprenants correspondant au nom de salle", content = @Content(schema = @Schema(implementation = ApprenantDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle  non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<ApprenantDto> searchByNumSalle(@PathVariable String numSalle) {
        List<ApprenantDto> apprenantDtoList = apprenantService.searchByNumSalle(numSalle);
        if (apprenantDtoList == null || apprenantDtoList.isEmpty()) {
              log.info("Aucune apprenant trouvée avec le nom de salle: {}", numSalle);
              throw new ResourceNotFoundException("Aucune Apprenant trouvée avec le nom  salle: " + numSalle);
        }
         log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, apprenantDtoList.size());
        return apprenantDtoList;
    }
    @Operation(summary = "Obtenir les apprenants paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée des formations")
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<ApprenantDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ApprenantDto> pageClasses = apprenantService.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
                pageClasses.getContent(),
                pageClasses.getNumber(),
                pageClasses.getTotalElements(),
                pageClasses.getTotalPages()
        );
    }

}
