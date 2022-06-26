package com.kviz.scheduleryandextaxi.scheduler;

import com.kviz.scheduleryandextaxi.model.Coordinate;
import com.kviz.scheduleryandextaxi.properties.CoordinateProperties;
import com.kviz.scheduleryandextaxi.service.TaxiService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YandexScheduler {

    private final CoordinateProperties coordinateProperties;
    private final TaxiService taxiService;

    @Timed("schedulerTaxi")
    @Scheduled(fixedDelay = 30_000)
    public void updatePrice() {
        var startPoint = new Coordinate(coordinateProperties.getStartLongitude(), coordinateProperties.getStartLatitude());
        var endPoint = new Coordinate(coordinateProperties.getFinishLongitude(), coordinateProperties.getFinishLatitude());
        taxiService.getPrice(startPoint, endPoint);
    }
}
