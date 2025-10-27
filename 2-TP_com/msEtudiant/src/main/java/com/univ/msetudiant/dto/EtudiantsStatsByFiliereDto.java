package com.univ.msetudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EtudiantsStatsByFiliereDto {
    private Long filiereId;
    private Long count;
}
