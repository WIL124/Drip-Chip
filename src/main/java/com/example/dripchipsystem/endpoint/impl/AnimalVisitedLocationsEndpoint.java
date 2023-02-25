package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.AnimalVisitedLocationDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.service.impl.AnimalVisitedLocationsService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/animals/{animalId}/locations")
@Validated
public class AnimalVisitedLocationsEndpoint
        extends AbstractEndpoint<AnimalVisitedLocationsService, AnimalVisitedLocationDto> {
    public AnimalVisitedLocationsEndpoint(AnimalVisitedLocationsService service) {
        super(service);
    }

    @GetMapping
    public List<AnimalVisitedLocationDto> getVisitedLocationsWithFilter(@PathVariable @NotNull @DecimalMin(value = "0", inclusive = false) Long animalId,
                                                                        @RequestParam(name = "startDateTime", required = false) LocalDateTime startDateTime,
                                                                        @RequestParam(name = "endDateTime", required = false) LocalDateTime endDateTime,
                                                                        @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                                                                        @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {
        return service.searchVisitedLocations(animalId, startDateTime, endDateTime, from, size);
    }

    @PostMapping("/{pointId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalVisitedLocationDto create(@PathVariable @Positive Long animalId,
                                           @PathVariable @Positive Long pointId) {
        return service.create(animalId, pointId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id,
                       @PathVariable @NotNull @Positive Long animalId) {
        service.delete(id);
    }
}
