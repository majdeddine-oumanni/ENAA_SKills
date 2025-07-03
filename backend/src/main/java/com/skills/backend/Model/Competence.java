package com.skills.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "competence")
    List<SubCompetence> subCompetenceList = new ArrayList<>();

    public boolean isAcquired() {
        return subCompetenceList != null &&
                !subCompetenceList.isEmpty() &&
                //subCompetenceList.stream().allMatch(sub -> sub.isValidated());
                subCompetenceList.stream().allMatch(SubCompetence::isValidated);
    }

    /* public boolean SimpleIsAcquired(){
        if(subCompetenceList == null || subCompetenceList.isEmpty()){
            return false;
        }
        for (SubCompetence sc: subCompetenceList){
            if (!sc.isValidated()){
                return false;
            }
        }
        return true;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCompetence> getSubCompetenceList() {
        return subCompetenceList;
    }

    public void setSubCompetenceList(List<SubCompetence> subCompetenceList) {
        this.subCompetenceList = subCompetenceList;
    }
}
