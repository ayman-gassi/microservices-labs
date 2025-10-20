package com.example.tp_com_ms.controller;

import com.example.tp_com_ms.dto.EtudiantReqDto;
import com.example.tp_com_ms.dto.EtudiantResDto;
import com.example.tp_com_ms.service.EtudiantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantService etudiantService;

    @GetMapping
    public ResponseEntity<List<EtudiantResDto>> getAllEtudiants() {
        List<EtudiantResDto> etudiants = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantResDto> getEtudiantById(@PathVariable long id) {
        EtudiantResDto etudiant = etudiantService.getEtudiantById(id);
        return ResponseEntity.ok(etudiant);
    }

    @PostMapping
    public ResponseEntity<EtudiantResDto> createEtudiant(@RequestBody EtudiantReqDto etudiantReqDto) {
        EtudiantResDto createdEtudiant = etudiantService.addEtudiant(etudiantReqDto);
        return new ResponseEntity<>(createdEtudiant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantResDto> updateEtudiant(@PathVariable long id, @RequestBody EtudiantReqDto etudiantReqDto) {
        EtudiantResDto updatedEtudiant = etudiantService.updateEtudiant(id, etudiantReqDto);
        return ResponseEntity.ok(updatedEtudiant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }
}
