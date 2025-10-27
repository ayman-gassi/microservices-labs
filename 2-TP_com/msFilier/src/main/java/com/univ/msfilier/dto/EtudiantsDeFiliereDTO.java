package com.univ.msfilier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EtudiantsDeFiliereDTO {
    private Long id;
    private String titre;
    private String cycle;
    private List<EtudiantDTO> etudiants = new ArrayList<>();
}
