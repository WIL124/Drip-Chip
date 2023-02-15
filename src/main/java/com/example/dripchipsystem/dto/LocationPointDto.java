package com.example.dripchipsystem.dto;

import com.example.dripchipsystem.model.LocationPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationPointDto implements Dto<LocationPoint> {
    private long id;
    private double latitude;
    private double longitude;
}
