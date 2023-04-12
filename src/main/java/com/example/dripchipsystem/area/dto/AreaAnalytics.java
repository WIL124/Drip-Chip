package com.example.dripchipsystem.area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AreaAnalytics {
    private Long totalQuantityAnimals;
    private Long totalAnimalsArrived;
    private Long totalAnimalsGone;
    private List<AnimalsAnalytics> animalsAnalytics;
}
