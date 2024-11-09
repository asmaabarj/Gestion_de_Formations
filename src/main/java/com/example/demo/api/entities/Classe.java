package com.example.demo.api.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


import lombok.Data;

@Data
@Entity
public class Classe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Le nom est obligatoire")
    @Column(length = 50)
    private String nom;
    
    @Positive(message = "Le numéro de salle doit être positif")
    private int numSalle;
    
    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apprenant> apprenants = new ArrayList<>();
    
    @OneToMany(mappedBy = "classe",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Formateur> formateurs = new ArrayList<>();
}
