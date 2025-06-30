package com.skills.backend.DTO;

import com.skills.backend.Model.SubCompetence;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDTO {
    private String name;
    private List<SubCompetence> competenceList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCompetence> getCompetenceList() {
        return competenceList;
    }

    public void setCompetenceList(List<SubCompetence> competenceList) {
        this.competenceList = competenceList;
    }
}
