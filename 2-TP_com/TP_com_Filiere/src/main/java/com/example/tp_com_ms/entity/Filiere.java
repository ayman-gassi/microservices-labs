package com.example.tp_com_ms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filieres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String intitule;

    private String description;

    @Enumerated(EnumType.STRING)
    private Cycle cycle;
}