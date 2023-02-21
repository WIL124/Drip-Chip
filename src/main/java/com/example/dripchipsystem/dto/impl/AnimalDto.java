package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDto extends AbstractDto {
    @Builder
    public AnimalDto(Long id, List<Long> animalTypes, float weight, float length, float height,
                     Gender gender, LifeStatus lifeStatus, LocalDateTime chippingDateTime,
                     Long chipperId, Long chippingLocationId, List<Long> visitedLocationsId,
                     LocalDateTime deathDateTime) {
        super(id);
        this.animalTypes = animalTypes;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.gender = gender;
        this.lifeStatus = lifeStatus;
        this.chippingDateTime = chippingDateTime;
        this.chipperId = chipperId;
        this.chippingLocationId = chippingLocationId;
        this.visitedLocationsId = visitedLocationsId;
        this.deathDateTime = deathDateTime;
    }
    @NotNull
    private List<Long> animalTypes;
    @NotNull
    private Float weight;
    @NotNull
    private Float length;
    @NotNull
    private Float height;
    @NotNull
    private Gender gender;
    @NotNull
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    @NotNull
    private LocalDateTime chippingDateTime;
    @NotNull
    private Long chipperId;
    @NotNull
    private Long chippingLocationId;
    @NotNull
    private List<Long> visitedLocationsId;
    private LocalDateTime deathDateTime;
}
