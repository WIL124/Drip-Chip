package com.example.dripchipsystem.locationPoint.endpoint;

import com.example.dripchipsystem.common.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.locationPoint.dto.LocationPointDto;
import com.example.dripchipsystem.locationPoint.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/locations")
@Validated
public class LocationEndpoint
        extends AbstractEndpoint<LocationService, LocationPointDto> {
    public LocationEndpoint(LocationService service) {
        super(service);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationPointDto create(@Valid @RequestBody LocationPointDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public LocationPointDto update(@PathVariable @NotNull @Positive Long id,
                                   @RequestBody @Valid LocationPointDto dto) {
        return service.updateEntity(id, dto);
    }

    @GetMapping("/{id}")
    public LocationPointDto getEntity(@PathVariable @NotNull @Positive Long id) {
        return service.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
    }
}
