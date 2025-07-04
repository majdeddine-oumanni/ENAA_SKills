package com.skills.backend.Service;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetenceServiceTest {

    // Step 1: Create mocks (fake objects)
    CompetenceRepository repository = mock(CompetenceRepository.class);
    CompetenceMapper mapper = mock(CompetenceMapper.class);
    SubCompetenceRepository subCompetenceRepository = mock(SubCompetenceRepository.class);
    SubCompetenceMapper subCompetenceMapper = mock(SubCompetenceMapper.class);

    // Step 2: Create the service using the mocks
    CompetenceService service = new CompetenceService(mapper, repository, subCompetenceRepository, subCompetenceMapper);

    @Test
    public void testPostWithValues() {

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

    @Test
    public void testTheGetAll(){
        Competence entity1 = new Competence();
        entity1.setId(1L);
        entity1.setName("Angular");
        entity1.setDescription("frontend technology");

        Competence entity2 = new Competence();
        entity2.setId(2L);
        entity2.setName("Spring Boot");
        entity2.setDescription("Spring Boot Intro");

        List<Competence> entityList = List.of(entity1, entity2);

        CompetenceDTO competenceDTO1 = new CompetenceDTO();
        competenceDTO1.setName("Angular");
        competenceDTO1.setDescription("frontend technology");

        CompetenceDTO competenceDTO2 = new CompetenceDTO();
        competenceDTO2.setName("Spring Boot");
        competenceDTO2.setDescription("Spring Boot Intro");

        List<CompetenceDTO> expectedDtoList = List.of(competenceDTO1, competenceDTO2);

        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDTOs(entityList)).thenReturn(expectedDtoList);

        List<CompetenceDTO> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("Angular", result.get(0).getName());
        assertEquals("Spring Boot", result.get(1).getName());
    }

    @Test
    void testUpdateTheCompetence(){
        Long id = 1L;
        CompetenceDTO enteredDTO = new CompetenceDTO();
        enteredDTO.setName("docker");
        enteredDTO.setDescription("app container");

        Competence existingCompetence = new Competence();
        existingCompetence.setId(id);
        existingCompetence.setName("Podman");
        existingCompetence.setDescription("api container");

        Competence afterUpdate = new Competence();
        afterUpdate.setId(id);
        afterUpdate.setName("docker");
        afterUpdate.setDescription("app container");

        CompetenceDTO returnedDTO = new CompetenceDTO();
        returnedDTO.setName("docker");
        returnedDTO.setDescription("app container");

        when(repository.findById(id)).thenReturn(Optional.of(existingCompetence));
        when(repository.save(existingCompetence)).thenReturn(afterUpdate);
        when(mapper.toDTO(afterUpdate)).thenReturn(returnedDTO);

        CompetenceDTO result = service.update(id, enteredDTO);

        assertEquals("docker", result.getName());
        assertEquals("app container", result.getDescription());
    }

    @Test
    public void testDeleteCompetence() {
        Long id = 1L;

        // Step 1: No need to mock anything because deleteById is void

        // Step 2: Call the method
        service.delete(id);

        // Step 3: Verify that repository.deleteById was called once with the correct id
        verify(repository).deleteById(id);
    }

}