package com.skills.backend.Service;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Repository.CompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceMapper mapper;
    private final CompetenceRepository repository;

    public CompetenceService(CompetenceMapper mapper, CompetenceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CompetenceDTO post(CompetenceDTO dto){
        Competence competence = mapper.toEntity(dto);
        Competence savedCompetence = repository.save(competence);
        return mapper.toDTO(savedCompetence);
    }

    public CompetenceDTO update(Long id, CompetenceDTO dto){
        Competence competence = repository.findById(id)
                .orElseThrow(()->new RuntimeException("competence not found"));
        competence.setName(dto.getName());
        CompetenceDTO competenceDTO = mapper.toDTO(repository.save(competence));
        return competenceDTO;
    }

    public List<CompetenceDTO> getAll(){
        List<Competence> competenceList = repository.findAll();
        return mapper.toDTOs(competenceList);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
