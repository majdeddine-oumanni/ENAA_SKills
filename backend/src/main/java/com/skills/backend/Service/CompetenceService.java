package com.skills.backend.Service;

import com.skills.backend.Mapper.CompetenceMapper;
import com.skills.backend.Repository.CompetenceRepository;
import org.springframework.stereotype.Service;

@Service
public class CompetenceService {
    private final CompetenceMapper mapper;
    private final CompetenceRepository repository;

    public CompetenceService(CompetenceMapper mapper, CompetenceRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

}
