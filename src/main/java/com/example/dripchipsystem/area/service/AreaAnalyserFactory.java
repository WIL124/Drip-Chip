package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.location.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class AreaAnalyserFactory {
    private final AreaValidator areaValidator;
    private final LocationRepository locationRepository;
    public AreaAnalyser createAnalyser(Area area, LocalDate startDate, LocalDate endDate) {
        return new AreaAnalyser(area, startDate, endDate, areaValidator, locationRepository.findAll());
    }
}
