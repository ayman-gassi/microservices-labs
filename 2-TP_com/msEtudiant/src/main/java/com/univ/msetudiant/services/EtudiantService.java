package com.univ.msetudiant.services;

import com.univ.msetudiant.dto.EtudiantReqDto;
import com.univ.msetudiant.dto.EtudiantResDto;
import com.univ.msetudiant.dto.EtudiantsStatsByFiliereDto;
import com.univ.msetudiant.dto.FiliereDto;
import com.univ.msetudiant.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EtudiantService {
    EtudiantResDto getEtudiantById(Long id);
    EtudiantResDto addEtudiant(EtudiantReqDto etudiantReqDto);
    List<Etudiant> getEtudiantsByIdFiliere(Long idFiliere);

    // New operations
    Page<Etudiant> list(String nom, String prenom, String email, Long filiereId, Pageable pageable);
    EtudiantResDto update(Long id, EtudiantReqDto etudiantReqDto);
    EtudiantResDto patch(Long id, Map<String, Object> fields);
    void delete(Long id);
    Page<Etudiant> getEtudiantsByIdFiliere(Long idFiliere, Pageable pageable);
    EtudiantResDto transfer(Long id, Long toFiliereId);
    List<EtudiantsStatsByFiliereDto> statsByFiliere();
    FiliereDto getFiliereOfEtudiant(Long id);
}
