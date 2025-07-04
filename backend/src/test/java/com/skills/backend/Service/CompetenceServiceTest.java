package com.skills.backend.Service;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CompetenceServiceTest {

    @Test
    public void testPostWithValues() {
        // Step 1: Create mocks (fake objects)
        CompetenceRepository repository = mock(CompetenceRepository.class);
        CompetenceMapper mapper = mock(CompetenceMapper.class);
        SubCompetenceRepository subCompetenceRepository = mock(SubCompetenceRepository.class);
        SubCompetenceMapper subCompetenceMapper = mock(SubCompetenceMapper.class);

        // Step 2: Create the service using the mocks
        CompetenceService service = new CompetenceService(mapper, repository, subCompetenceRepository, subCompetenceMapper);

        // Step 3: Create real example values
        CompetenceDTO inputDTO = new CompetenceDTO();
        inputDTO.setName("Java");
        inputDTO.setDescription("Java Basics");

        Competence entity = new Competence();
        entity.setName("Java");
        entity.setDescription("Java Basics");

        Competence savedEntity = new Competence();
        savedEntity.setId(1L);
        savedEntity.setName("Java");
        savedEntity.setDescription("Java Basics");

        CompetenceDTO outputDTO = new CompetenceDTO();
        outputDTO.setName("Java");
        outputDTO.setDescription("Java Basics");

        // Step 4: Define what mocks should do when called
        when(mapper.toEntity(inputDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDTO(savedEntity)).thenReturn(outputDTO);

        // Step 5: Call the method under test
        CompetenceDTO result = service.post(inputDTO);

        // Step 6: Check the result is what we expected
        assertEquals(outputDTO.getName(), result.getName());
        assertEquals(outputDTO.getDescription(), result.getDescription());
    }
}