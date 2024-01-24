package com.catchboock.catchbook.controller;

import com.catchboock.catchbook.entity.Place;
import com.catchboock.catchbook.service.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlaceController {

    private final PlaceService service;

    public PlaceController(PlaceService service) {
        this.service = service;
    }

    @PostMapping("/addPlace")
    ResponseEntity<String> addPlace(@RequestBody Place place) {
        return service.addPlace(place);
    }

    @GetMapping("/getAll")
    List<Place> getAll() {
        return service.getAll();
    }
    @PatchMapping("/patchPlace/{id}")
    void patchPlace(@PathVariable Long id, @RequestBody Place place) {
        service.patchPlace(id, place);
    }

    @DeleteMapping("/deletePlace/{id}")
    ResponseEntity<String> deletePlace(@PathVariable Long id) {
        return service.deletePlace(id);
    }
}
