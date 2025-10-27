package com.univ.msetudiant.web;

import com.univ.msetudiant.dto.EtudiantReqDto;
import com.univ.msetudiant.dto.EtudiantResDto;
import com.univ.msetudiant.dto.EtudiantsStatsByFiliereDto;
import com.univ.msetudiant.dto.FiliereDto;
import com.univ.msetudiant.entities.Etudiant;
import com.univ.msetudiant.mappers.EtudiantMapper;
import com.univ.msetudiant.services.EtudiantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    final EtudiantService etudiantService;
    final EtudiantMapper mapper;

    public EtudiantController(EtudiantService etudiantService, EtudiantMapper mapper) {
        this.etudiantService = etudiantService;
        this.mapper = mapper;
    }


    // List with optional filters and pagination
    @GetMapping
    public Page<Etudiant> list(@RequestParam(required = false) String nom,
                               @RequestParam(required = false) String prenom,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Long filiereId,
                               @PageableDefault(size = 10) Pageable pageable) {
        return etudiantService.list(nom, prenom, email, filiereId, pageable);
    }

    @GetMapping("/{id}")
    public EtudiantResDto findById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id);
    }

    @PostMapping("/add")
    public EtudiantResDto add(@RequestBody EtudiantReqDto etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    // Canonical create endpoint
    @PostMapping
    public EtudiantResDto create(@RequestBody EtudiantReqDto etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @GetMapping("/filiere/{idFiliere}")
    public List<Etudiant> getStudentsByFiliere(@PathVariable Long idFiliere) {
        return etudiantService.getEtudiantsByIdFiliere(idFiliere);
    }

    // Paged students by filiere
    @GetMapping("/filiere/{idFiliere}/page")
    public Page<Etudiant> getStudentsByFilierePaged(@PathVariable Long idFiliere,
                                                    @PageableDefault(size = 10) Pageable pageable) {
        return etudiantService.getEtudiantsByIdFiliere(idFiliere, pageable);
    }

    // Update full student
    @PutMapping("/{id}")
    public EtudiantResDto update(@PathVariable Long id, @RequestBody EtudiantReqDto dto) {
        return etudiantService.update(id, dto);
    }

    // Partial update
    @PatchMapping("/{id}")
    public EtudiantResDto patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return etudiantService.patch(id, fields);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        etudiantService.delete(id);
    }

    // Transfer student to another filiere
    @PostMapping("/{id}/transfer")
    public EtudiantResDto transfer(@PathVariable Long id, @RequestParam Long toFiliereId) {
        return etudiantService.transfer(id, toFiliereId);
    }

    // Stats by filiere
    @GetMapping("/stats/by-filiere")
    public List<EtudiantsStatsByFiliereDto> statsByFiliere() {
        return etudiantService.statsByFiliere();
    }

    // Get filiere details of a student
    @GetMapping("/{id}/filiere")
    public FiliereDto getFiliereOfEtudiant(@PathVariable Long id) {
        return etudiantService.getFiliereOfEtudiant(id);
    }
}
