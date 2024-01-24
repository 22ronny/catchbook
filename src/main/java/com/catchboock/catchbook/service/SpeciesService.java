package com.catchboock.catchbook.service;


import com.catchboock.catchbook.entity.Species;
import com.catchboock.catchbook.repository.SpeciesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeciesService {

    private final SpeciesRepository repository;
    private final CheckExistCounter checkExistCounter;

    public SpeciesService(SpeciesRepository repository, CheckExistCounter checkExistCounter) {
        this.repository = repository;
        this.checkExistCounter = checkExistCounter;
    }

    public void addSpecies(Species species) {
        repository.save(species);
    }

    public List<Species> getAllSpeciesForFishType(Long id) {
        return repository.findAllSpeciesByFishTypeId(id);
    }

    public List<Species> getAllSpeciesAndFishType() {
        return repository.findAll();
    }

    public void patchSpecies(Long id, Species patchSpezies) {
        repository.findById(id).map(species -> {species.setName(patchSpezies.getName());
            return repository.save(species);
        }).orElseGet(() ->{
            patchSpezies.setId(id);
            return repository.save(patchSpezies);
        });
    }

    public void deleteSpecies(Long id) {
        int size = checkExistCounter.checkExistCount(id, "species");
        if (size == 0) {
            repository.deleteById(id);
        } else {
            String species = repository.findById(id).get().getName();
            throw new RuntimeException(species + " wird: " + size + " mal in der Fangliste verwendet und kann daher nicht gelöscht werden!");
        }
    }
}
