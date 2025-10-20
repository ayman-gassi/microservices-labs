package com.example.tp_com_ms.service;

import com.example.tp_com_ms.dto.FiliereDTO;
import com.example.tp_com_ms.entity.Filiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiliereService {
    Filiere create(FiliereDTO dto);
    Filiere update(Long id, FiliereDTO dto);
    Filiere getById(Long id);
    Page<Filiere> getAll(Pageable pageable);
    void delete(Long id);
}
