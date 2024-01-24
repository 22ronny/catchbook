package com.catchboock.catchbook.controller;


import com.catchboock.catchbook.service.BaitService;
import com.catchboock.catchbook.entity.Bait;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BaitController {

    private final BaitService service;

    public BaitController(BaitService service) {
        this.service = service;
    }

    @PostMapping("/addBait")
    void addBait(@RequestBody Bait newBait) {
        service.addBait(newBait);
    }
    @DeleteMapping("/deleteBait/{id}")
    void deleteBait(@PathVariable Long id) {
        service.deleteBait(id);
    }

    @GetMapping("/getAllBaitsForFishType/{id}")
    List<Bait> getAllBaitsForFishType(@PathVariable Long id) {
        return service.getAllBaitsForFishType(id);
    }
    @GetMapping("/getAllBaitsAndFishType")
    List<Bait> getAllBaitsAndFishType() {
        return service.getAllBaitsAndFishType();
    }
    @PatchMapping("/patchBait/{id}")
    void patchBait(@PathVariable Long id, @RequestBody Bait bait) {
        service.patchBait(id, bait);
    }
}
