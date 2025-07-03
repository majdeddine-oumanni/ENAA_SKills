package com.skills.backend.Mapper;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.Model.Competence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetenceMapper{
    Competence toEntity(CompetenceDTO dto);
    @Mapping(target = "acquired", expression = "java(competence.isAcquired())")
    CompetenceDTO toDTO(Competence competence);
    List<CompetenceDTO> toDTOs(List<Competence> competenceList);
}
