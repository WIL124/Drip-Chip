package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.model.LifeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime chippingDateTime;
    @NotNull
    @Positive
    private Long chipperId;
    @NotNull
    @Positive
    private Long chippingLocationId;
    private List<Long> visitedLocations;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime deathDateTime;

    @Builder
    public AnimalDto(Long id, List<Long> animalTypes, float weight, float length, float height,
                     Gender gender, LifeStatus lifeStatus, OffsetDateTime chippingDateTime,
                     Long chipperId, Long chippingLocationId, List<Long> visitedLocations,
                     OffsetDateTime deathDateTime) {
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
