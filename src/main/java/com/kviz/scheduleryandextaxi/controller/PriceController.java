package com.kviz.scheduleryandextaxi.controller;

import com.kviz.scheduleryandextaxi.model.db.MomentPrice;
import com.kviz.scheduleryandextaxi.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PriceController {
    private final TaxiService taxiService;

    @GetMapping("/prices")
    public List<MomentPrice> getAllPrice(){
        return taxiService.getAllPrice();
    }
}
