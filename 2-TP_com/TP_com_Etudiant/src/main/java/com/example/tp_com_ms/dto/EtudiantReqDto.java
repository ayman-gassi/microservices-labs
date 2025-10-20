package com.example.tp_com_ms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantReqDto {
    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private Long filiereId;
}
