package com.skills.backend.Repository;

import com.skills.backend.Model.SubCompetence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCompetenceRepository extends JpaRepository<SubCompetence, Long> {
    List<SubCompetence> findAllByCompetence_Id(Long id);
}
