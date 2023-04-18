package com.example.dripchipsystem.area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AreaAnalytics {
    public static final AreaAnalytics EMPTY = new AreaAnalytics(0L,0L,0L,new ArrayList<>());
    private Long totalQuantityAnimals;
    private Long totalAnimalsArrived;
    private Long totalAnimalsGone;
    private List<AnimalsAnalytics> animalsAnalytics;
}
