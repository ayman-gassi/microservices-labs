package com.univ.msetudiant.services;

import com.univ.msetudiant.dto.EtudiantReqDto;
import com.univ.msetudiant.dto.EtudiantResDto;
import com.univ.msetudiant.dto.FiliereDto;
import com.univ.msetudiant.entities.Etudiant;
import com.univ.msetudiant.mappers.EtudiantMapper;
import com.univ.msetudiant.repositories.EtudiantRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.time.LocalDate;
import java.util.stream.Collectors;
import com.univ.msetudiant.dto.EtudiantsStatsByFiliereDto;

@Service
public class EtudiantServiceImp implements EtudiantService{

    final EtudiantRepo repo;
    final EtudiantMapper mapper;
    final EtudiantRepo etudiantRepo;
    final WebClient webClient;

    public EtudiantServiceImp(EtudiantRepo repo, EtudiantMapper mapper, EtudiantRepo etudiantRepo, WebClient webClient) {
        this.repo = repo;
        this.mapper = mapper;
        this.etudiantRepo = etudiantRepo;
        this.webClient = webClient;
    }

    @Override
    public EtudiantResDto getEtudiantById(Long id) {
        Optional<Etudiant> etudiant = repo.findById(id);
        if(etudiant.isPresent()){
            Etudiant etudiant1 = etudiant.get();
            EtudiantResDto etudiantResDto = mapper.toDto(etudiant1);
            //--------------Filiere------------
            FiliereDto filiereDto = webClient.get()
                    .uri("http://localhost:8080/api/filiere/"+etudiant1.getFiliereId())
                    .retrieve()
                    .bodyToMono(FiliereDto.class)
                    .block();
            //---------------------------------
            etudiantResDto.setFiliere(filiereDto);
            return etudiantResDto;

            //return mapper.toDto(etudiant1);
        }else{
            throw new EntityNotFoundException("Etudiant not found");
        }
    }



    @Override
    public EtudiantResDto addEtudiant(EtudiantReqDto etudiantReqDto) {
        return mapper.toDto(etudiantRepo.save(mapper.toEntity(etudiantReqDto)));
    }

    @Override
    public List<Etudiant> getEtudiantsByIdFiliere(Long idFiliere) {
        return etudiantRepo.findAllByFiliereId(idFiliere);
    }

    @Override
    public Page<Etudiant> list(String nom, String prenom, String email, Long filiereId, Pageable pageable) {
        Etudiant probe = Etudiant.builder()
                .nom(nom)
                .prenom(prenom)
                .email(email)
                .filiereId(filiereId)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withMatcher("nom", m -> m.contains().ignoreCase())
                .withMatcher("prenom", m -> m.contains().ignoreCase())
                .withMatcher("email", m -> m.contains().ignoreCase());
        return etudiantRepo.findAll(Example.of(probe, matcher), pageable);
    }

    @Override
    public EtudiantResDto update(Long id, EtudiantReqDto dto) {
        Etudiant e = etudiantRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant not found"));
        e.setNom(dto.getNom());
        e.setPrenom(dto.getPrenom());
        e.setEmail(dto.getEmail());
        e.setTelephone(dto.getTelephone());
        e.setDateNaissance(dto.getDateNaissance());
        e.setFiliereId(dto.getFiliereId());
        Etudiant saved = etudiantRepo.save(e);
        EtudiantResDto res = mapper.toDto(saved);
        FiliereDto filiereDto = fetchFiliere(saved.getFiliereId());
        res.setFiliere(filiereDto);
        return res;
    }

    @Override
    public EtudiantResDto patch(Long id, Map<String, Object> fields) {
        Etudiant e = etudiantRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant not found"));
        if (fields.containsKey("nom")) e.setNom((String) fields.get("nom"));
        if (fields.containsKey("prenom")) e.setPrenom((String) fields.get("prenom"));
        if (fields.containsKey("email")) e.setEmail((String) fields.get("email"));
        if (fields.containsKey("telephone")) e.setTelephone((String) fields.get("telephone"));
        if (fields.containsKey("dateNaissance")) e.setDateNaissance(LocalDate.parse((String) fields.get("dateNaissance")));
        if (fields.containsKey("filiereId")) e.setFiliereId(Long.valueOf(String.valueOf(fields.get("filiereId"))));
        Etudiant saved = etudiantRepo.save(e);
        EtudiantResDto res = mapper.toDto(saved);
        FiliereDto filiereDto = fetchFiliere(saved.getFiliereId());
        res.setFiliere(filiereDto);
        return res;
    }

    @Override
    public void delete(Long id) {
        if (!etudiantRepo.existsById(id)) throw new EntityNotFoundException("Etudiant not found");
        etudiantRepo.deleteById(id);
    }

    @Override
    public Page<Etudiant> getEtudiantsByIdFiliere(Long idFiliere, Pageable pageable) {
        return etudiantRepo.findAllByFiliereId(idFiliere, pageable);
    }

    @Override
    public EtudiantResDto transfer(Long id, Long toFiliereId) {
        Etudiant e = etudiantRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant not found"));
        e.setFiliereId(toFiliereId);
        Etudiant saved = etudiantRepo.save(e);
        EtudiantResDto res = mapper.toDto(saved);
        FiliereDto filiereDto = fetchFiliere(saved.getFiliereId());
        res.setFiliere(filiereDto);
        return res;
    }

    @Override
    public List<EtudiantsStatsByFiliereDto> statsByFiliere() {
        return etudiantRepo.countEtudiantsByFiliere().stream()
                .map(fc -> EtudiantsStatsByFiliereDto.builder()
                        .filiereId(fc.getFiliereId())
                        .count(fc.getCount())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDto getFiliereOfEtudiant(Long id) {
        Etudiant e = etudiantRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant not found"));
        return fetchFiliere(e.getFiliereId());
    }

    private FiliereDto fetchFiliere(Long filiereId) {
        if (filiereId == null) return null;
        return webClient.get()
                .uri("http://localhost:8080/api/filiere/" + filiereId)
                .retrieve()
                .bodyToMono(FiliereDto.class)
                .block();
    }


}
