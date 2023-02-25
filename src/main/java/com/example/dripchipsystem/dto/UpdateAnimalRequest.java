package com.example.dripchipsystem.dto;

import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.model.LifeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAnimalRequest extends AnimalDto {
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Float weight;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Float length;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Float height;
    @NotNull
    private Gender gender;
    @NotNull
    private LifeStatus lifeStatus;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Long chipperId;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Long chippingLocationId;
}
