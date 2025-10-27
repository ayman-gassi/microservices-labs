package com.univ.msfilier.mappers;

import com.univ.msfilier.dto.EtudiantsDeFiliereDTO;
import com.univ.msfilier.dto.FiliereReq;
import com.univ.msfilier.dto.FiliereResp;
import com.univ.msfilier.entities.Filiere;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FiliereMapper {
Filiere toEntity(FiliereReq filiereReq);

FiliereResp toResp(Filiere filiere);

@Mapping(target = "etudiants", ignore = true)
EtudiantsDeFiliereDTO toDtoEtudiantsFiliere(Filiere filiere);
}
