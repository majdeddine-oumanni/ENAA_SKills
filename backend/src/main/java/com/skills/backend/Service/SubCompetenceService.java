package com.skills.backend.Service;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.springframework.stereotype.Service;

@Service
public class SubCompetenceService {
    private final SubCompetenceMapper mapper;
    private final SubCompetenceRepository repository;

    public SubCompetenceService(SubCompetenceMapper mapper, SubCompetenceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public SubCompetenceDTO add(SubCompetenceDTO dto){
        SubCompetence subCompetence = mapper.toEntity(dto);
        SubCompetence saved = repository.save(subCompetence);
        return mapper.toDTO(saved);
    }
}
