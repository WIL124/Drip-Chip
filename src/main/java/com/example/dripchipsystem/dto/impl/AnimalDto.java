package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.model.LifeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDto extends AbstractDto {
    @NotNull
    @NotEmpty
    private List<@NotNull @Positive Long> animalTypes;
    @NotNull
    @Positive
    private Float weight;
    @NotNull
    @Positive
    private Float length;
    @NotNull
    @Positive
    private Float height;
    @NotNull
    private Gender gender;
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime chippingDateTime;
    @NotNull
    @Positive
    private Long chipperId;
    @NotNull
    @Positive
    private Long chippingLocationId;
    private List<Long> visitedLocations;
    private LocalDateTime deathDateTime;

    @Builder
    public AnimalDto(Long id, List<Long> animalTypes, float weight, float length, float height,
                     Gender gender, LifeStatus lifeStatus, LocalDateTime chippingDateTime,
                     Long chipperId, Long chippingLocationId, List<Long> visitedLocations,
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
        this.visitedLocations = visitedLocations;
        this.deathDateTime = deathDateTime;
    }
}
