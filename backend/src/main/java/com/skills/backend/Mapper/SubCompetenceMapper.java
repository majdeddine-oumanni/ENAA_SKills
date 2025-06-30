package com.skills.backend.Mapper;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Model.SubCompetence;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubCompetenceMapper{
    SubCompetence toEntity(SubCompetenceDTO dto);
    SubCompetenceDTO toDTO(SubCompetence subCompetence);
    List<SubCompetenceDTO> toDTOs(List<SubCompetence> subCompetenceList);
}
