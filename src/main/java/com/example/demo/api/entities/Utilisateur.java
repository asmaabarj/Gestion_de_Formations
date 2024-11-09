package com.example.demo.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Utilisateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull(message = "Le nom est obligatoire")
    @Column(length = 50)
    private String nom;
    
    @NotNull(message = "Le prénom est obligatoire")
    @Column(length = 50)
    private String prenom;
    
    @NotNull(message = "L'email doit être valide")
    @Column(unique = true)
    private String email;
}
