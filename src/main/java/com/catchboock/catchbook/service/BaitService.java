package com.catchboock.catchbook.service;


import com.catchboock.catchbook.entity.Bait;
import com.catchboock.catchbook.repository.BaitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaitService {

    private final BaitRepository repository;
    private final CheckExistCounter checkExistCounter;

    public BaitService(BaitRepository repository, CheckExistCounter checkExistCounter) {
        this.repository = repository;
        this.checkExistCounter = checkExistCounter;
    }

    public void addBait(Bait newBait) {
        repository.save(newBait);
    }

    public List<Bait> getAllBaitsForFishType(Long id) {
        return repository.findAllBaitByFishTypeId(id);
    }

    public List<Bait> getAllBaitsAndFishType() {
        return repository.findAll();
    }

    public void patchBait(Long id, Bait patchBait) {
        repository.findById(id).map(bait -> {bait.setBait(patchBait.getBait());
        return repository.save(bait);}
        ).orElseGet(()->{
            patchBait.setId(id);
            return repository.save(patchBait);
        });
    }

    public void deleteBait(Long id) {
        int size = checkExistCounter.checkExistCount(id, "bait");
        if (size == 0) {
            repository.deleteById(id);
        } else {
            String bait = repository.findById(id).get().getBait();
            throw new RuntimeException(bait + " wird: " + size + " mal in der Fangliste verwendet und kann daher nicht gelöscht werden!");
        }
    }
}