package com.example.demo.api.entities.dto;

import com.example.demo.api.entities.Formateur;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormateurDto {

    private String nom;
    private String classeNom;
    private String prenom;
    private String email;
    private String specialite;

    private List<FormationDto> formationDtos;

    public static FormateurDto findById(Formateur formateur) {
        if (formateur == null) {
            return null;
        }
        List<FormationDto> formationDtos=formateur.getFormations()!=null?
                formateur.getFormations().stream()
                        .map(formation -> FormationDto.builder()
                                .titre(formation.getTitre())
                                .dateDebut(formation.getDateDebut())
                                .dateFin(formation.getDateFin())
                                .niveau(formation.getNiveau())
                                .build()
                        ).collect(Collectors.toList()) : Collections.emptyList();

        return FormateurDto.builder()
                .nom(formateur.getNom())
                .prenom(formateur.getPrenom())
                .email(formateur.getEmail())
                .specialite(formateur.getSpecialite())
                .classeNom(formateur.getClasse() != null ? formateur.getClasse().getNom() : null)
                .formationDtos(formationDtos)
                .build();
    }

    public static List<FormateurDto> getAll(List<Formateur> formateurs) {
        if (formateurs == null || formateurs.isEmpty()) {
            return null;
        }

        return formateurs.stream()
                .map(FormateurDto::findById)
                .collect(Collectors.toList());
    }
    public static Page<FormateurDto> getAllWithPagination(Page<Formateur> formateursPage) {
        return formateursPage.map(FormateurDto::findById);
    }
}
