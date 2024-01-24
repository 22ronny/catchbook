package com.catchboock.catchbook.service;

import com.catchboock.catchbook.entity.Place;
import com.catchboock.catchbook.repository.PlaceRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository repository;
    private final CheckExistCounter checkExistCounter;


    public PlaceService(PlaceRepository repository, CheckExistCounter checkExistCounter) {
        this.repository = repository;
        this.checkExistCounter = checkExistCounter;
    }

    public ResponseEntity<String> addPlace(Place place) {
        if (repository.existsByPlace(place.getPlace())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Platz schon vorhanden!");
        }
        repository.save(place);
        return ResponseEntity.ok().build();
    }

    public List<Place> getAll() {
        return  repository.findAll();
    }
    public void patchPlace(Long id, Place patchPlace) {
        repository.findById(id).map(place -> {place.setPlace(patchPlace.getPlace());
        return repository.save(place);
        }).orElseGet(() -> {
            patchPlace.setId(id);
            return repository.save(patchPlace);
        });
    }

    public ResponseEntity<String> deletePlace(Long id) {
        int size = checkExistCounter.checkExistCount(id, "place");
        if (size == 0) {
           repository.deleteById(id);
           return ResponseEntity.ok().build();
        } else {
            String place = repository.findById(id).get().getPlace();
            String errorMessage = place + " wird: " + size + " mal in der Fangliste verwendet und kann daher nicht gelöscht werden!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}
