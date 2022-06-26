package com.kviz.scheduleryandextaxi.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Option {
    private double price;
    private double min_price;
    private double waiting_time;
    private String class_name;
    private String price_text;
}
