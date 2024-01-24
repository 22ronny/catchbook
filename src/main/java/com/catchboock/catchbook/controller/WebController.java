package com.catchboock.catchbook.controller;

import com.catchboock.catchbook.DTO.CatchDto;
import com.catchboock.catchbook.repository.BaitRepository;
import com.catchboock.catchbook.repository.CatchRepository;
import com.catchboock.catchbook.service.CatchService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
public class WebController {

    private final CatchRepository repository;
    private final BaitRepository baitRepository;
    private final CatchService catchService;


    public WebController(CatchRepository repository, BaitRepository baitRepository, CatchService catchService) {
        this.repository = repository;
        this.baitRepository = baitRepository;
        this.catchService = catchService;
    }

    @GetMapping("/list")
    ModelAndView baitList() {
        ModelAndView mav = new ModelAndView("list.html");
        mav.addObject("baitsPREDATOR", baitRepository.findAllBaitByFishTypeId(2L));
        mav.addObject("baitsNON_PREDATOR", baitRepository.findAllBaitByFishTypeId(1L));
        return mav;
    }

//    @GetMapping("/catches")
//    ModelAndView catches() {
//        ModelAndView mav = new ModelAndView("catches.html");
//        mav.addObject("catches", catchService.getCatch(null, null, null, null, null, null));
//        return mav;
//    }

    @GetMapping("/catches")
    ModelAndView catches(
            @RequestParam(name = "fishTypeId", required = false) Long fishTypeId,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(name = "speciesId", required = false) Long speciesId,
            @RequestParam(name = "baitId", required = false) Long baitId,
            @RequestParam(name = "placeId", required = false) Long placeId) {
        ModelAndView mav = new ModelAndView("catches.html");
        mav.addObject("catches", catchService.getCatch(fishTypeId, startDate, endDate, speciesId, baitId, placeId));
        return mav;
    }

    @GetMapping("/catch/{id}")
    public ModelAndView postOne(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("catch");
        mav.addObject("catch", repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return mav;
    }
    @GetMapping("/")
    String index() {
        return "index.html";
    }
    @GetMapping("/place")
    String place() {
        return "place.html";
    }
    @GetMapping("/baits")
    String baits() {
        return "baits.html";
    }
    @GetMapping("/species")
    String species() {
        return "species.html";
    }
    @GetMapping("/catchFish")
    String catchFish() {
        return "catchFish.html";
    }
}
