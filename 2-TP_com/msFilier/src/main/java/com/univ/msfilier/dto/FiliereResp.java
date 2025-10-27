package com.univ.msfilier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FiliereResp {
    private Long id;
    private String titre;
    private String cycle;
}
