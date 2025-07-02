package com.skills.backend.Controller;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.Service.CompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competence")
public class CompetenceController {
    private final CompetenceService service;

    public CompetenceController(CompetenceService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<CompetenceDTO> getCompetenceList(){
        return service.getAll();
    }

    @PostMapping("/post")
    public CompetenceDTO post(@RequestBody CompetenceDTO dto){
        return service.post(dto);
    }

    @PutMapping("/update/{id}")
    public CompetenceDTO updateCompetence(@PathVariable Long id, @RequestBody CompetenceDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
