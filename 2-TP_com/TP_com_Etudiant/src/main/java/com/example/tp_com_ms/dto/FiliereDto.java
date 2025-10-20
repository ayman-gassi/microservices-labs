package com.example.tp_com_ms.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class FiliereDto {
    private Long id;

    private String code;

    private String intitule;

    private String description;

    @Enumerated(EnumType.STRING)
    private Cycle cycle;
}
