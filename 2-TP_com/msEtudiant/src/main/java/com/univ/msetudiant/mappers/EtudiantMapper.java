package com.univ.msetudiant.mappers;

import com.univ.msetudiant.dto.EtudiantReqDto;
import com.univ.msetudiant.dto.EtudiantResDto;
import com.univ.msetudiant.entities.Etudiant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EtudiantMapper {

    Etudiant toEntity(EtudiantReqDto etudiantReqDto);

    // IMPORTANT : on ignore 'filiere' (on l'attachera après)
    @Mapping(target = "filiere", ignore = true)
    EtudiantResDto toDto(Etudiant etudiant);
}
