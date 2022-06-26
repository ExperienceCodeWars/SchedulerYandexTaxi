package com.kviz.scheduleryandextaxi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Coordinate {
    public String longitude;
    public String latitude;

    @Override
    public String toString() {
        return longitude + "," + latitude;
    }
}
