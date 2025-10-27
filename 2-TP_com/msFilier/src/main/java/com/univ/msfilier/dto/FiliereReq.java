package com.univ.msfilier.dto;

import com.univ.msfilier.entities.Cycle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FiliereReq {
    private String titre;
    private Cycle cycle;
}
