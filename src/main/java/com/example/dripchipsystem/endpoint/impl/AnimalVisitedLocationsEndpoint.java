package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.UpdateVisitedLocationRequest;
import com.example.dripchipsystem.dto.childs.AnimalVisitedLocationDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.service.impl.AnimalVisitedLocationsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;
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
    public List<AnimalVisitedLocationDto> getVisitedLocationsWithFilter(@PathVariable @NotNull @Positive Long animalId,
                                                                        @RequestParam(name = "startDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDateTime,
                                                                        @RequestParam(name = "endDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDateTime,
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

    @DeleteMapping("/{visitedPointId}")
    public void delete(@PathVariable @NotNull @Positive Long visitedPointId,
                       @PathVariable @NotNull @Positive Long animalId) {
        service.delete(animalId, visitedPointId);
    }

    @PutMapping
    public AnimalVisitedLocationDto update(@PathVariable @Positive Long animalId,
                                           @RequestBody @Valid UpdateVisitedLocationRequest request) {
        return service.update(animalId, request);
    }
}
