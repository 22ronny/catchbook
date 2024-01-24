package com.catchboock.catchbook.service;

import com.catchboock.catchbook.entity.FishType;
import com.catchboock.catchbook.repository.FishTypeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class FishTypeService {

    private final FishTypeRepository repository;

    public FishTypeService(FishTypeRepository repository) {
        this.repository = repository;
    }
    @PostConstruct //Funktion wird bei start ausgeführt und muss nicht aufgerufen werden
    public void init() {
        FishType nonPredator = new FishType(1L, "NON_PREDATOR");
        FishType predator = new FishType(2L, "PREDATOR");

        repository.save(nonPredator);
        repository.save(predator);
    }
}
