package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class AreaValidatorTest {
    private AreaValidator areaValidator = new AreaValidator();

    @Test
    void validateArea() {
        areaValidator.validateArea(List.of(new LocationPointDto(1L, 12.0, 12.0), new LocationPointDto(2L, 12.0, 12.0)).stream().collect(Collectors.toList()));
    }
}