package com.catchboock.catchbook.service;

import com.catchboock.catchbook.DTO.CatchDto;
import com.catchboock.catchbook.entity.Catch;
import com.catchboock.catchbook.repository.CatchRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatchService {

    private final CatchRepository repository;

    public CatchService(CatchRepository repository) {
        this.repository = repository;
    }

    public void addCatch(Catch fishCatch) {
        repository.save(fishCatch);
    }

    public List<CatchDto> getCatch(Long fishTypeId, LocalDateTime startDate, LocalDateTime endDate, Long speciesId, Long baitId, Long placeId) {
//        return repository.findAll(CatchSpecifications.withFilters(fishTypeId, startDate, endDate, speciesId, baitId, placeId));
        List<Catch> catches = repository.findAll(CatchSpecifications.withFilters(fishTypeId, startDate, endDate, speciesId, baitId, placeId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        return catches.stream()
                .map(aCatch -> new CatchDto(
                        aCatch.getId(),
                        aCatch.getBait(),
                        aCatch.getSpecies(),
                        aCatch.getWeight(),
                        aCatch.getLength(),
                        aCatch.getPlace(),
                        aCatch.getCatchTime().format(formatter)))
                .collect(Collectors.toList());
    }

    public Catch patchCatch(Long id, Catch patchCatch) {
        return repository.findById(id).map(element -> {
            element.setCatchTime(patchCatch.getCatchTime());
            element.setBait(patchCatch.getBait());
            element.setSpecies(patchCatch.getSpecies());
            element.setWeight(patchCatch.getWeight());
            element.setLength(patchCatch.getLength());
            element.setPlace(patchCatch.getPlace());
            return repository.save(element);
        }).orElseGet(()-> {
            patchCatch.setId(id);
            return repository.save(patchCatch);
        });
    }

//public Catch patchCatch(Long id, Catch patchCatch) {
//    return repository.findById(id).map(existingCatch -> {
//        BeanUtils.copyProperties(patchCatch, existingCatch);
//        existingCatch.setId(id);
//        return repository.save(existingCatch);
//    }).orElseGet(() -> {
//        patchCatch.setId(id);
//        return repository.save(patchCatch);
//    });
//}

}
