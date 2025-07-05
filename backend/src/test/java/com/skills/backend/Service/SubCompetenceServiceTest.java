package com.skills.backend.Service;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class SubCompetenceServiceTest {
    SubCompetenceMapper mapper = mock(SubCompetenceMapper.class);

    SubCompetenceRepository repository = mock(SubCompetenceRepository.class);

    CompetenceRepository competenceRepository = mock(CompetenceRepository.class);

    SubCompetenceService service = new SubCompetenceService(mapper, repository, competenceRepository);

    @Test
    void addSubCompetenceTest() {
        SubCompetenceDTO dto = new SubCompetenceDTO();
        dto.setName("OOP");
        dto.setCompetence_id(1L);

        SubCompetence entity = new SubCompetence();
        entity.setName("OOP");

        Competence competence = new Competence();
        competence.setId(1L);

        SubCompetence saved = new SubCompetence();
        saved.setId(1L);
        saved.setName("OOP");
        saved.setCompetence(competence);

        SubCompetenceDTO resultDto = new SubCompetenceDTO();
        resultDto.setName("OOP");

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(resultDto);

        SubCompetenceDTO result = service.add(dto);

        assertEquals("OOP", result.getName());
        verify(repository).save(entity);
    }

    @Test
    void updateValidation() {
        Long id = 1L;
        SubCompetenceDTO inputDTO = new SubCompetenceDTO();
        inputDTO.setName("swagger");

        SubCompetence existingEntity = new SubCompetence();
        existingEntity.setId(id);
        existingEntity.setName("postman");

        SubCompetence updatedEntity = new SubCompetence();
        updatedEntity.setId(id);
        updatedEntity.setName("swagger");

        SubCompetenceDTO returnedDTO = new SubCompetenceDTO();
        returnedDTO.setName("swagger");

        when(repository.findById(id)).thenReturn(Optional.of(existingEntity));
        when(repository.save(existingEntity)).thenReturn(updatedEntity);
        when(mapper.toDTO(updatedEntity)).thenReturn(returnedDTO);

        SubCompetenceDTO result = service.update(id, inputDTO);

        assertEquals("swagger", result.getName());
    }

    @Test
    void getAllSubCompetenceListTest() {
        SubCompetence s1 = new SubCompetence();
        s1.setName("A");
        SubCompetence s2 = new SubCompetence();
        s2.setName("B");

        List<SubCompetence> list = List.of(s1, s2);

        SubCompetenceDTO dto1 = new SubCompetenceDTO();
        dto1.setName("A");
        SubCompetenceDTO dto2 = new SubCompetenceDTO();
        dto2.setName("B");

        when(repository.findAll()).thenReturn(list);
        when(mapper.toDTOs(list)).thenReturn(List.of(dto1, dto2));

        List<SubCompetenceDTO> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getName());
        assertEquals("B", result.get(1).getName());
    }

    @Test
    void deleteSubCompetenceTest() {
        Long id = 1L;

        service.delete(id);

        verify(repository).deleteById(id);
    }
}