package com.univ.msfilier.services;

import com.univ.msfilier.dto.EtudiantDTO;
import com.univ.msfilier.dto.EtudiantsDeFiliereDTO;
import com.univ.msfilier.dto.FiliereReq;
import com.univ.msfilier.dto.FiliereResp;
import com.univ.msfilier.entities.Filiere;
import com.univ.msfilier.mappers.FiliereMapper;
import com.univ.msfilier.repositories.FiliereRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FiliereServImpl implements FiliersService{
    private FiliereRepo filiereRepo;
    private FiliereMapper filiereMapper;
    private WebClient webClient;

    public FiliereServImpl(FiliereRepo filiereRepo, FiliereMapper filiereMapper, WebClient webClient) {
        this.filiereRepo = filiereRepo;
        this.filiereMapper = filiereMapper;
        this.webClient = webClient;
    }

    @Override
    public FiliereResp addFiliere(FiliereReq filiere) {
        Filiere f = filiereRepo.save(filiereMapper.toEntity(filiere));
        return filiereMapper.toResp(f);
    }

    @Override
    public FiliereResp findFiliere(Long filiereId) {
        Optional<Filiere> optionalFiliere = filiereRepo.findById(filiereId);
        if(optionalFiliere.isPresent()){
            return filiereMapper.toResp(optionalFiliere.get());
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    @Override
    public EtudiantsDeFiliereDTO etudiantsDeFiliere(Long filiereId) {
        Optional<Filiere> optionalFiliere = filiereRepo.findById(filiereId);
        if(optionalFiliere.isPresent()){
            Filiere filiere = optionalFiliere.get();
            EtudiantsDeFiliereDTO etudiantsDeFiliereDTO = filiereMapper.toDtoEtudiantsFiliere(filiere);
            //------------ Récuperation de liste des étudiants de cette filière----
            List<EtudiantDTO> etudiants = new ArrayList<>();
            Flux<EtudiantDTO> etudiantsDTOFlux = webClient.get()
                    .uri("http://localhost:8081/api/etudiant/filiere/"+filiereId)
                    .retrieve()
                    .bodyToFlux(EtudiantDTO.class);
            //Conversion de Flux vers Liste d'étudiants
            etudiants = etudiantsDTOFlux.collect(Collectors.toList())
                    .share()
                    .block();

            etudiantsDeFiliereDTO.setEtudiants(etudiants);
            return etudiantsDeFiliereDTO;

            //-------------------------------------------------------------
        }else{
            throw new EntityNotFoundException("Not found");
        }
    }

    @Override
    public Page<FiliereResp> list(Pageable pageable) {
        return filiereRepo.findAll(pageable).map(filiereMapper::toResp);
    }

    @Override
    public FiliereResp update(Long id, FiliereReq req) {
        Filiere f = filiereRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        f.setTitre(req.getTitre());
        f.setCycle(req.getCycle());
        return filiereMapper.toResp(filiereRepo.save(f));
    }

    @Override
    public FiliereResp patch(Long id, String titre, String cycle) {
        Filiere f = filiereRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        if (titre != null) f.setTitre(titre);
        if (cycle != null) {
            try {
                f.setCycle(Enum.valueOf(com.univ.msfilier.entities.Cycle.class, cycle.toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid cycle value: " + cycle);
            }
        }
        return filiereMapper.toResp(filiereRepo.save(f));
    }

    @Override
    public void delete(Long id) {
        if (!filiereRepo.existsById(id)) throw new EntityNotFoundException("Not found");
        filiereRepo.deleteById(id);
    }
}
