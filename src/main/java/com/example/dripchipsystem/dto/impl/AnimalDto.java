package com.example.dripchipsystem.dto.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnimalDto extends AbstractDto {

    @NotNull
    @NotEmpty
    private List<@NotNull @DecimalMin(value = "0", inclusive = false) Long> animalTypes;
    @NotNull
    private Float weight;
    @NotNull
    private Float length;
    @NotNull
    private Float height;
    @NotNull
    private Gender gender;
    private LifeStatus lifeStatus = LifeStatus.ALIVE;
    @CreatedDate
    private LocalDateTime chippingDateTime;
    @NotNull
    private Long chipperId;
    @NotNull
    private Long chippingLocationId;
    private List<Long> visitedLocationsId;
    private LocalDateTime deathDateTime;

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
}
