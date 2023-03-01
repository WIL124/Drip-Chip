package com.example.dripchipsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateVisitedLocationRequest {
    @NotNull
    @Positive
    private Long visitedLocationPointId;
    @NotNull
    @Positive
    private Long locationPointId;
}
