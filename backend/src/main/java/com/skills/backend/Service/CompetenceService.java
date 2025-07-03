package com.skills.backend.Service;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceService {
    private final CompetenceMapper mapper;
    private final CompetenceRepository repository;
    private final SubCompetenceRepository subCompetenceRepository;
    private final SubCompetenceMapper subCompetenceMapper;

    public CompetenceService(CompetenceMapper mapper, CompetenceRepository repository, SubCompetenceRepository subCompetenceRepository, SubCompetenceMapper subCompetenceMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.subCompetenceRepository = subCompetenceRepository;
        this.subCompetenceMapper = subCompetenceMapper;
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
        competence.setDescription(dto.getDescription());
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

    public List<SubCompetenceDTO> getByCompetence(Long id){
        List<SubCompetence> subCompetenceList = subCompetenceRepository.findAllByCompetence_Id(id);
        List<SubCompetenceDTO> subCompetenceDTOList = subCompetenceMapper.toDTOs(subCompetenceList);
        return subCompetenceDTOList;
    }

    public CompetenceDTO getById(Long id){
        Competence foundCompetence = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("competence not found"));
        return mapper.toDTO(foundCompetence);
    }
}
