package com.catchboock.catchbook.controller;

import com.catchboock.catchbook.DTO.CatchDto;
import com.catchboock.catchbook.entity.Catch;
import com.catchboock.catchbook.service.CatchService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatchController {

    private final CatchService service;

    public CatchController(CatchService service) {
        this.service = service;
    }

    @RequestMapping("/addCatch")
    void addCatch(@RequestBody Catch fishCatch) {
        service.addCatch(fishCatch);
    }

    @RequestMapping("/getCatch")
    List<CatchDto> getCatch(
            @RequestParam(required = false) Long fishTypeId,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Long speciesId,
            @RequestParam(required = false) Long baitId,
            @RequestParam(required = false) Long placeId) {
        return service.getCatch(fishTypeId, startDate, endDate, speciesId, baitId, placeId);
    }

    @RequestMapping("/patchCatch/{id}")
    Catch patchCatch(@PathVariable Long id, @RequestBody Catch patchCatch) {
        return service.patchCatch(id, patchCatch);
    }
}
