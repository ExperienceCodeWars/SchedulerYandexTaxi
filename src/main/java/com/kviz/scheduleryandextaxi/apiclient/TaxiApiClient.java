package com.kviz.scheduleryandextaxi.apiclient;

import com.kviz.scheduleryandextaxi.model.Price;
import io.micrometer.core.annotation.Timed;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "yandexclient", url = "${yandex.url}")
public interface TaxiApiClient {

    @GetMapping
    @Timed("gettingPriceFromYandex")
    Price getPrice(@RequestParam String clid, @RequestParam String apikey, @RequestParam String rll);
}
