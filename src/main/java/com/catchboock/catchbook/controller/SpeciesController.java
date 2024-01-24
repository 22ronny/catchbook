package com.catchboock.catchbook.controller;


import com.catchboock.catchbook.entity.Species;
import com.catchboock.catchbook.service.SpeciesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpeciesController {

    private final SpeciesService service;

    public SpeciesController(SpeciesService service) {
        this.service = service;
    }

    @PostMapping("/addSpecies")
    void addSpecies(@RequestBody Species species) {
        service.addSpecies(species);
    }

    @GetMapping("/getAllSpeciesForFishType/{id}")
    List<Species> getAllSpeciesForFishType(@PathVariable Long id) {
        return service.getAllSpeciesForFishType(id);
    }

    @GetMapping("/getAllSpeciesAndFishType")
    List<Species> getAllSpeciesAndFishType() {
        return  service.getAllSpeciesAndFishType();
    }

    @PatchMapping("/patchSpecies/{id}")
    void patchSpecies(@PathVariable Long id, @RequestBody Species species) {
        service.patchSpecies(id, species);
    }
    @DeleteMapping("/deleteSpecies/{id}")
    void deleteSpecies(@PathVariable Long id) {
        service.deleteSpecies(id);
    }
}
