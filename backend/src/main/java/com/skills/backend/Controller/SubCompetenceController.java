package com.skills.backend.Controller;

import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Service.SubCompetenceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subCompetence")
public class SubCompetenceController {
    private final SubCompetenceService service;

    public SubCompetenceController(SubCompetenceService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<SubCompetenceDTO> getAll(){
        return service.getAll();
    }

    @PostMapping("/post")
    public SubCompetenceDTO post(@RequestBody SubCompetenceDTO dto){
        return service.add(dto);
    }

    @PutMapping("/update/{id}")
    public SubCompetenceDTO update(@PathVariable Long id, @RequestBody SubCompetenceDTO dto){
        return service.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
