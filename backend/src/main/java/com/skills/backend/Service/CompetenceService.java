package com.skills.backend.Service;

import com.skills.backend.DTO.CompetenceDTO;
import com.skills.backend.DTO.SubCompetenceDTO;
import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Mapper.SubCompetenceMapper;
import com.skills.backend.Model.Competence;
import com.skills.backend.Model.SubCompetence;
import com.skills.backend.Repository.CompetenceRepository;
import com.skills.backend.Repository.SubCompetenceRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
        return mapper.toDTO(repository.save(competence));
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

    public void exportProgressReport(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=progress_report.xlsx");

        List<Competence> competenceList = repository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Progress Report");

        // Header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Competence");
        header.createCell(1).setCellValue("Sub-Competence");
        header.createCell(2).setCellValue("Validated");

        int rowNum = 1;

        for (Competence competence : competenceList) {
            List<SubCompetence> subCompetences = subCompetenceRepository.findAllByCompetence_Id(competence.getId());
            for (SubCompetence sub : subCompetences) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(competence.getName());
                row.createCell(1).setCellValue(sub.getName());
                row.createCell(2).setCellValue(sub.isValidated() ? "Yes" : "No");
            }
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
