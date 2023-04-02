package com.example.dripchipsystem.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class UpdateAnimalTypeRequest {
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Long oldTypeId;
    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Long newTypeId;
}
