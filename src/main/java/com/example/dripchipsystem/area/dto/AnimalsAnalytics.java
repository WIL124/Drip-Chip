package com.example.dripchipsystem.area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnimalsAnalytics {
    private String animalType;
    private Long animalTypeId;
    private Long quantityAnimals;
    private Long animalsArrived;
    private Long animalsGone;
}
