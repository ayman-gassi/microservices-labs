package com.example.tp_com_ms.service;

import com.example.tp_com_ms.dto.EtudiantReqDto;
import com.example.tp_com_ms.dto.EtudiantResDto;
import com.example.tp_com_ms.entity.Etudiant;
import com.example.tp_com_ms.mapper.EtudiantMapper;
import com.example.tp_com_ms.repository.EtudiantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepo etudiantRepo;
    private final EtudiantMapper etudiantMapper;

    @Override
    public EtudiantResDto getEtudiantById(long id) {
        Etudiant etudiant = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));
        return etudiantMapper.toDto(etudiant);
    }

    @Override
    public EtudiantResDto addEtudiant(EtudiantReqDto etudiantReqDto) {
        Etudiant etudiant = etudiantMapper.toEntity(etudiantReqDto);
        Etudiant savedEtudiant = etudiantRepo.save(etudiant);
        return etudiantMapper.toDto(savedEtudiant);
    }

    @Override
    public List<EtudiantResDto> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepo.findAll();
        return etudiants.stream()
                .map(etudiantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EtudiantResDto updateEtudiant(long id, EtudiantReqDto etudiantReqDto) {
        Etudiant existingEtudiant = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));

        // Update fields
        existingEtudiant.setNom(etudiantReqDto.getNom());
        existingEtudiant.setPrenom(etudiantReqDto.getPrenom());
        existingEtudiant.setEmail(etudiantReqDto.getEmail());
        existingEtudiant.setTelephone(etudiantReqDto.getTelephone());
        existingEtudiant.setFiliereId(etudiantReqDto.getFiliereId());

        Etudiant updatedEtudiant = etudiantRepo.save(existingEtudiant);
        return etudiantMapper.toDto(updatedEtudiant);
    }

    @Override
    public void deleteEtudiant(long id) {
        Etudiant etudiant = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + id));
        etudiantRepo.delete(etudiant);
    }
}