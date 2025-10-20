package com.example.tp_com_ms.dto;

import com.example.tp_com_ms.entity.Cycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiliereDTO {

    private String code;

    private String intitule;

    private String description;

    @Enumerated(EnumType.STRING)
    private Cycle cycle;
}