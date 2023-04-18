package com.example.dripchipsystem.area.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalsAnalytics {
    private String animalType;
    private Long animalTypeId;
    private Long quantityAnimals;
    private Long animalsArrived;
    private Long animalsGone;
    public AnimalsAnalytics sum(AnimalsAnalytics second){
        this.quantityAnimals += second.getQuantityAnimals();
        this.animalsGone += second.getAnimalsGone();
        this.animalsArrived += second.getAnimalsArrived();
        return this;
    }
}
