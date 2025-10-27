package com.univ.msfilier.services;

import com.univ.msfilier.dto.EtudiantsDeFiliereDTO;
import com.univ.msfilier.dto.FiliereReq;
import com.univ.msfilier.dto.FiliereResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiliersService {
    FiliereResp addFiliere(FiliereReq filiere);
    FiliereResp findFiliere(Long filiereId);
    EtudiantsDeFiliereDTO etudiantsDeFiliere(Long filiereId);
    Page<FiliereResp> list(Pageable pageable);
    FiliereResp update(Long id, FiliereReq req);
    FiliereResp patch(Long id, String titre, String cycle);
    void delete(Long id);

}
