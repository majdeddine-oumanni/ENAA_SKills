package com.skills.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SubCompetenceDTO {

    private String name;
    private boolean validated;
    private Long competence_id;

    public String getName() {
        return name;
    }

    public Long getCompetence_id() {
        return competence_id;
    }

    public void setCompetence_id(Long competence_id) {
        this.competence_id = competence_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
