package com.kviz.scheduleryandextaxi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Price {
    private List<Option> options;
    private String currency;
    private double distance;
    public  String time_text;
}
