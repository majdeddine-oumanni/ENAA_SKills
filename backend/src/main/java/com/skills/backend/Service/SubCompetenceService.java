package com.skills.backend.Service;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SubCompetenceDTO update(Long id, SubCompetenceDTO dto){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("subCompetence not found"));
        subCompetence.setName(dto.getName());
        subCompetence.setValidated(dto.isValidated());
        return mapper.toDTO(repository.save(subCompetence));
    }

    public List<SubCompetenceDTO> getAll(){
        List<SubCompetence> subCompetenceList = repository.findAll();
        List<SubCompetenceDTO> subCompetenceDTOList = mapper.toDTOs(subCompetenceList);
        return subCompetenceDTOList;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
