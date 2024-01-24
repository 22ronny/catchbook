package com.catchboock.catchbook.service;

import com.catchboock.catchbook.DTO.CatchDto;
import com.catchboock.catchbook.entity.Catch;
import com.catchboock.catchbook.service.CatchService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckExistCounter {

    private final CatchService catchService;

    public CheckExistCounter(CatchService catchService) {
        this.catchService = catchService;
    }

    public int checkExistCount(Long id, String objekt) {
        Long fishTypeId = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        Long speciesId = null;
        Long baitId = null;
        Long placeId = null;
        if (objekt.equals("bait")) {
            baitId = id;
        } else if (objekt.equals("place")) {
            placeId = id;
        } else if (objekt.equals("species")) {
            speciesId = id;
        }
        List<CatchDto> catchesWithObjekt = catchService.getCatch(fishTypeId, startDate, endDate, speciesId, baitId, placeId);
        return catchesWithObjekt.size();
    }
}
