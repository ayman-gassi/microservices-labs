package com.example.tp_com_ms.mapper;
import com.example.tp_com_ms.dto.EtudiantReqDto;
import com.example.tp_com_ms.dto.EtudiantResDto;
import com.example.tp_com_ms.entity.Etudiant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EtudiantMapper {
    Etudiant toEntity(EtudiantReqDto etudiantReqDto);
    EtudiantResDto toDto(Etudiant etudiant);
}
