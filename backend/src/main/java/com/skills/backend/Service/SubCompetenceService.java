package com.skills.backend.Service;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCompetenceService {
    private final SubCompetenceMapper mapper;
    private final SubCompetenceRepository repository;
    private final CompetenceRepository competenceRepository;

    public SubCompetenceService(SubCompetenceMapper mapper, SubCompetenceRepository repository, CompetenceRepository competenceRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.competenceRepository = competenceRepository;
    }

    public SubCompetenceDTO add(SubCompetenceDTO dto){
        SubCompetence subCompetence = mapper.toEntity(dto);
        Competence competence = competenceRepository.findById(dto.getCompetence_id())
                .orElseThrow(()->new RuntimeException("competence not found"));
        subCompetence.setCompetence(competence);
        SubCompetence saved = repository.save(subCompetence);
        return mapper.toDTO(saved);
    }

    public SubCompetenceDTO update(Long id, SubCompetenceDTO dto){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("subCompetence not found"));
        subCompetence.setName(dto.getName());
        return mapper.toDTO(repository.save(subCompetence));
    }

    public SubCompetenceDTO updateValidation(Long id, boolean isValidated){
        SubCompetence subCompetence = repository.findById(id)
                .orElseThrow(()->new RuntimeException("SubCompetence not found"));
        subCompetence.setValidated(isValidated);
        SubCompetence saved = repository.save(subCompetence);
        return mapper.toDTO(saved);
    }

    public List<SubCompetenceDTO> getAll(){
        List<SubCompetence> subCompetenceList = repository.findAll();
        return mapper.toDTOs(subCompetenceList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
