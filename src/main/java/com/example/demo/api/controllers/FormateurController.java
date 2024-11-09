package com.example.demo.api.controllers;


import com.example.demo.api.entities.Formateur;
import com.example.demo.api.entities.dto.FormateurDto;
import com.example.demo.api.services.impl.FormateurServiceImpl;
import com.example.demo.api.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/formateurs")
@RequiredArgsConstructor
public class FormateurController {
    private final FormateurServiceImpl formateurService;

    @PostMapping
    public Formateur createFormateur(@Valid @RequestBody Formateur formateur){
//        log.info("Création d'une nouvelle Formateur : {}", formateur);
        Formateur createdFormateur = formateurService.createFormateur(formateur);
//        log.info("Formateur créée avec succès : {}", createdFormateur);
        return createdFormateur;
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFormateur(@PathVariable Long id, @Valid @RequestBody Formateur formateur) {
//        try {
            Optional<Formateur> check = formateurService.getById(id);
            if (check.isPresent()) {
                formateur.setId(id);
//                log.info("Mise à jour du formateur avec l'ID : {}", id);
                formateurService.updateFormateur(formateur);
//                throw new ClassSucesseExption("formateur update avec succès : " + id);

//            } else {
//                throw new ClasseNotFoundException("Aucun formateur trouvé avec l'ID : " + id);
//            }
//        } catch (ClasseNotFoundException e) {
//            throw new ClasseNotFoundException("Erreur lors de la mise à jour : corps de la requête manquant ou invalide");
       }
    }
    @DeleteMapping("/{id}")
    public void deleteFormateur(@PathVariable Long id) {
        Optional<Formateur> check= formateurService.getById(id);
        if (check.isPresent()){
            formateurService.deleteFormateur(id);
            //log.info("formateur supprimer avec succès : {}", id);
            //throw new ClassSucesseExption("Formateur Supprimer avec succès : " + id);

        }else {
            //throw new ClasseNotFoundException("Aucune Formateur trouvée avec le numéro : " + id);

        }
    }

    @GetMapping
    public List<FormateurDto> getAllFormateur(){

        return  formateurService.getAllFormateur();


    }

    @GetMapping("/{id}")
    public FormateurDto getFormateurById(@PathVariable Long id) {
      //  try {
            return formateurService.getById(id)
                    .map(FormateurDto::findById)
                    .orElse(null);
      //  } catch (ClasseNotFoundException e) {
       //     log.error("Classe non trouvée avec l'ID: {}", id);
      //      throw e;
       // }
    }

    @Operation(summary = "Rechercher une formateur par numSalle de salle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des Formateur correspondant au nom de salle", content = @Content(schema = @Schema(implementation = FormateurDto.class))),
            @ApiResponse(responseCode = "404", description = "Salle  non trouvée")
    })
    @GetMapping("/search/{numSalle}")
    @ResponseStatus(HttpStatus.OK)
    public List<FormateurDto> searchByNumSalle(@PathVariable String numSalle) {
        List<FormateurDto> formateurDtoList = formateurService.searchByNumSalle(numSalle);
        if (formateurDtoList == null || formateurDtoList.isEmpty()) {
          //  log.info("Aucune formateur trouvée avec le nom de salle: {}", numSalle);
         //   throw new ClasseNotFoundException("Aucune Formateur trouvée avec le nom  salle: " + numSalle);
        }
       // log.info("Classes trouvées avec le numéro de salle {}: {}", numSalle, formateurDtoList.size());
        return formateurDtoList;
    }
    @Operation(summary = "Obtenir les Formateurs paginées")
    @ApiResponse(responseCode = "200", description = "Liste paginée des formations")
    @GetMapping("/page")
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<FormateurDto> getAllClassesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FormateurDto> pageClasses = formateurService.getAllClassesWithPagination(pageable);

        return new PageResponse<>(
                pageClasses.getContent(),
                pageClasses.getNumber(),
                pageClasses.getTotalElements(),
                pageClasses.getTotalPages()
        );
    }
}
