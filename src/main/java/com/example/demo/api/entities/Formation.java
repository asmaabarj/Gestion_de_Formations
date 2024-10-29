package com.example.demo.api.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.validation.constraints.Future;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;

import com.example.demo.api.entities.enums.StatutFormation;

import lombok.Data;

@Data
@Entity
public class Formation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
//    @NotNull(message = "Le titre est obligatoire")
    @Column(length = 100)
    private String titre;
    
    @Column(length = 50)
    private String niveau;
    
    @Column(length = 255)
    private String prerequis;
    
//    @Min(value = 1, message = "La capacité minimum doit être au moins 1")
    private int capaciteMin;
    
//    @Min(value = 1, message = "La capacité maximum doit être au moins 1")
    private int capaciteMax;
    
//    @Future(message = "La date de début doit être dans le futur")
    private LocalDate dateDebut;
    
//    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;
    
    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL)
    private List<Apprenant> apprenants = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutFormation statut = StatutFormation.PLANIFIEE;
}
