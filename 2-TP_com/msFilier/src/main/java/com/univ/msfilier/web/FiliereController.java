package com.univ.msfilier.web;

import com.univ.msfilier.dto.EtudiantsDeFiliereDTO;
import com.univ.msfilier.dto.FiliereResp;
import com.univ.msfilier.dto.FiliereReq;
import com.univ.msfilier.services.FiliersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/filiere")
public class FiliereController {
    private FiliersService filieresService;
    public FiliereController(FiliersService filieresService) {
        this.filieresService = filieresService;
    }

    // List filières (paged)
    @GetMapping
    Page<FiliereResp> list(@PageableDefault(size = 10) Pageable pageable) {
        return filieresService.list(pageable);
    }

    // Create filière
    @PostMapping
    FiliereResp create(@RequestBody FiliereReq req) {
        return filieresService.addFiliere(req);
    }

    @GetMapping("/{id}")
    FiliereResp getFiliere(@PathVariable Long id) {
        return filieresService.findFiliere(id);
    }

    @GetMapping("/etudiants/{idFiliere}")
    EtudiantsDeFiliereDTO getEtudiantsDeFiliere(@PathVariable Long idFiliere) {
        return filieresService.etudiantsDeFiliere(idFiliere);
    }

    // Update full
    @PutMapping("/{id}")
    FiliereResp update(@PathVariable Long id, @RequestBody FiliereReq req) {
        return filieresService.update(id, req);
    }

    // Patch partial (titre and/or cycle)
    @PatchMapping("/{id}")
    FiliereResp patch(@PathVariable Long id,
                      @RequestParam(required = false) String titre,
                      @RequestParam(required = false) String cycle) {
        return filieresService.patch(id, titre, cycle);
    }

    // Delete
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        filieresService.delete(id);
    }
}
