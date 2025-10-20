package com.example.tp_com_ms.controller;

import com.example.tp_com_ms.dto.FiliereDTO;
import com.example.tp_com_ms.entity.Filiere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.example.tp_com_ms.service.FiliereService;

@RestController
@RequestMapping("/api/filiere")
public class FiliereController {
    private final FiliereService service;

    public FiliereController(FiliereService filiereService) {
        this.service = filiereService;
    }
    @GetMapping("/{id}")
    public Filiere getFiliere(@PathVariable Long id){
        return service.getById(id);
    }
    @PostMapping("/add")
    public Filiere create(@RequestBody FiliereDTO dto) {
        return service.create(dto);
    }
    @GetMapping("/")
    public Page<Filiere> list(@org.springframework.data.web.PageableDefault(page = 0, size = 10)Pageable pageable){
        return service.getAll(pageable);
    }

}
