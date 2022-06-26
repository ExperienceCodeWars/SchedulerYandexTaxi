package com.kviz.scheduleryandextaxi.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yandex")
@Data
public class YandexProperties {
    private String clid;
    private String apiKey;
}
