package com.example.tp_com_ms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Long filiereId;
}