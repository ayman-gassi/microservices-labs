package com.example.tp_com_ms.service;

import com.example.tp_com_ms.dto.FiliereDTO;
import com.example.tp_com_ms.entity.Filiere;
import jakarta.transaction.Transactional;
import com.example.tp_com_ms.mapper.FiliereMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import com.example.tp_com_ms.repository.FiliereRepository;

@Service
@Transactional
public class FiliereServiceImpl implements FiliereService{
    private final FiliereRepository repository;
    private final FiliereMapper mapper;

    public FiliereServiceImpl(FiliereRepository repository, FiliereMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Filiere create(FiliereDTO dto) {
        Filiere entity = mapper.toEntity(dto);
        return repository.save(entity);
    }

    @Override
    public Filiere update(Long id, FiliereDTO dto) {
        Filiere existing = repository.findById(id)
                .orElseThrow(() -> new ExpressionException("Filiere not found with id " + id));
        existing.setCode(dto.getCode());
        existing.setIntitule(dto.getIntitule());
        existing.setDescription(dto.getDescription());
        existing.setCycle(dto.getCycle());
        return repository.save(existing);
    }

    @Override
    public Filiere getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ExpressionException("Filiere not found with id " + id));
    }

    @Override
    public Page<Filiere> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ExpressionException("Filiere not found with id " + id);
        }
        repository.deleteById(id);
    }
}
