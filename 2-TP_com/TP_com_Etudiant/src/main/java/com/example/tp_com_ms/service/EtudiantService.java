package com.example.tp_com_ms.service;

import com.example.tp_com_ms.dto.EtudiantReqDto;
import com.example.tp_com_ms.dto.EtudiantResDto;

import java.util.List;
public interface EtudiantService {
    EtudiantResDto getEtudiantById(long id);
    EtudiantResDto addEtudiant(EtudiantReqDto etudiantReqDto);
    List<EtudiantResDto> getAllEtudiants();
    EtudiantResDto updateEtudiant(long id, EtudiantReqDto etudiantReqDto);
    void deleteEtudiant(long id);
}