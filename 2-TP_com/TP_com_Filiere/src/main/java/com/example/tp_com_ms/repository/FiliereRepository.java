package com.example.tp_com_ms.repository;

import com.example.tp_com_ms.entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Optional<Filiere> findByCode(String code);
}
