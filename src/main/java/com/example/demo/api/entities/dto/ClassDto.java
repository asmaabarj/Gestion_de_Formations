package com.example.demo.api.entities.dto;

import com.example.demo.api.entities.Classe;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ClassDto {

        private String nom;
        private int numSalle;

        private List<FormateurDto> formateurs;

        public static ClassDto FindById(Classe classe) {
            if (classe == null) {
                return null;
            }

            List<FormateurDto> formateurDtos = classe.getFormateurs() != null ?
                    classe.getFormateurs().stream()
                            .map(formateur -> FormateurDto.builder()
                                    .nom(formateur.getNom())
                                    .build())
                            .collect(Collectors.toList()) :
                    Collections.emptyList();

            return ClassDto.builder()
                    .nom(classe.getNom())
                    .numSalle(classe.getNumSalle())
                    .formateurs(formateurDtos)
                    .build();
        }

        public static List<ClassDto> getAll(List<Classe> classes) {
            if (classes == null || classes.isEmpty()) {
                return null;
            }

            return classes.stream()
                    .map(ClassDto::FindById)
                    .collect(Collectors.toList());
        }

        public static Page<ClassDto> getAllWithPagination(Page<Classe> classePage) {
            return classePage.map(ClassDto::FindById);
        }
    }

