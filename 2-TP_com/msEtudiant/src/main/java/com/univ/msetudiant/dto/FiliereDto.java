package com.univ.msetudiant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FiliereDto {
    private Long id;
    private String titre;
    private Cycle cycle;
}
