package com.example.dripchipsystem.area.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AreaPointDto {
    @NotNull
    @Min(-180)
    @Max(180)
    @JsonProperty("longitude")
    private double x;

    @NotNull
    @Min(-90)
    @Max(90)
    @JsonProperty("latitude")
    private double y;
}
