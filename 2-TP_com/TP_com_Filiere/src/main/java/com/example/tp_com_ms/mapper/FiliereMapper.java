package com.example.tp_com_ms.mapper;

import com.example.tp_com_ms.dto.FiliereDTO;
import com.example.tp_com_ms.entity.Filiere;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FiliereMapper {
    FiliereDTO toDto(Filiere filiere);
    Filiere toEntity(FiliereDTO dto);
}
