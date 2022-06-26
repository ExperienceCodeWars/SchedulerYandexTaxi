package com.kviz.scheduleryandextaxi.service;

import com.kviz.scheduleryandextaxi.apiclient.TaxiApiClient;
import com.kviz.scheduleryandextaxi.model.Coordinate;
import com.kviz.scheduleryandextaxi.model.db.MomentPrice;
import com.kviz.scheduleryandextaxi.properties.YandexProperties;
import com.kviz.scheduleryandextaxi.repository.PriceRepository;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TaxiService {

    private final YandexProperties yandexProperties;
    private final TaxiApiClient taxiApiClient;
    private final PriceRepository priceRepository;
    private AtomicInteger price;

    public TaxiService(YandexProperties yandexProperties, TaxiApiClient taxiApiClient, PriceRepository priceRepository, MeterRegistry meterRegistry) {
        this.yandexProperties = yandexProperties;
        this.taxiApiClient = taxiApiClient;
        this.priceRepository = priceRepository;
        price = new AtomicInteger();
        meterRegistry.gauge("priceTaxi", price);
    }

    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        var rll = startPoint.toString() + "~" + endPoint.toString();

        var currentPrice = taxiApiClient.getPrice(yandexProperties.getClid(), yandexProperties.getApiKey(), rll);

        if (currentPrice.getOptions().isEmpty())
            throw new RuntimeException("Options are empty");

        var priceDouble = currentPrice.getOptions().get(0).getPrice();
        price.set((int) priceDouble);

        var momentPrice = new MomentPrice(LocalDateTime.now(ZoneId.of("Europe/Moscow")), priceDouble);
        priceRepository.save(momentPrice);
    }

    public List<MomentPrice> getAllPrice() {
        return priceRepository.findAll();
    }
}
