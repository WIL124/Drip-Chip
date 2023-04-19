package com.example.dripchipsystem.location.endpoint;

import com.example.dripchipsystem.common.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.location.dto.LocationPointDto;
import com.example.dripchipsystem.location.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.security.NoSuchAlgorithmException;

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

    @GetMapping
    public Long getByCoordinates(@RequestParam(name = "longitude") Double longitude,
                                 @RequestParam(name = "latitude") Double latitude) {
        return service.getIdByCoordinates(latitude, longitude);
    }

    @GetMapping("/geohash")
    public String getGeoHash(@RequestParam(name = "longitude") Double longitude,
                             @RequestParam(name = "latitude") Double latitude) {
        return service.getGeoHash(latitude, longitude);
    }

    @GetMapping("/geohashv2")
    public String getGeoHashV2(@RequestParam(name = "longitude") Double longitude,
                               @RequestParam(name = "latitude") Double latitude) {
        return service.getGeoHashV2(latitude, longitude);
    }

    @GetMapping("/geohashv3")
    public String getGeoHashV3(@RequestParam(name = "longitude") Double longitude,
                               @RequestParam(name = "latitude") Double latitude) throws NoSuchAlgorithmException {
        return service.getGeoHashV3(latitude, longitude);
    }
}
