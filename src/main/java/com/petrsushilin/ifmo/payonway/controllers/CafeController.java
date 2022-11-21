package com.petrsushilin.ifmo.payonway.controllers;

import com.petrsushilin.ifmo.payonway.entity.Cafe;
import com.petrsushilin.ifmo.payonway.service.CafeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cafe")
public class CafeController {
    private final CafeService cafeService;

    @PostMapping(path = "/post")
    public void registerCafe(@RequestBody Cafe cafe) {
        cafeService.registerCafe(cafe);
    }

    @GetMapping(path = "/get/id={cafeId}")
    public ResponseEntity<Cafe> getCafe(@PathVariable Long cafeId) {
        return ResponseEntity.ok(cafeService.getCafe(cafeId));
    }

    @PutMapping(path = "/update/id={cafeId}/baseInfo")
    public void updateCafe(@PathVariable Long cafeId,
                           @RequestParam(required = false) String nameOfShop,
                           @RequestParam(required = false) String theLawAddress) {
        cafeService.updateCafe(cafeId, nameOfShop, theLawAddress);
    }

    @PutMapping(path = "/change/id={cafeId}/addActualAddress")
    public void addActualAddresses(@PathVariable Long cafeId,
                                   @RequestParam List<String> actualAddress) {
        cafeService.addActualAddresses(cafeId, actualAddress);
    }

    @DeleteMapping(path = "/delete/id={cafeId}")
    public void deleteCafe(@PathVariable Long cafeId) {
        cafeService.remove(cafeId);
    }

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }
}
