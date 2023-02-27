package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AnimalUpdateRequest;
import com.example.dripchipsystem.dto.UpdateAnimalTypeDto;
import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.Gender;
import com.example.dripchipsystem.model.LifeStatus;
import com.example.dripchipsystem.service.impl.AnimalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/animals")
@Validated
public class AnimalEndpoint
        extends AbstractEndpoint<AnimalService, AnimalDto> {
    public AnimalEndpoint(AnimalService service) {
        super(service);
    }

    @GetMapping("/search")
    public List<AnimalDto> search(@RequestParam(name = "startDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime startDateTime,
                                  @RequestParam(name = "endDateTime", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime endDateTime,
                                  @RequestParam(name = "chipperId", required = false) Integer chipperId,
                                  @RequestParam(name = "chippingLocationId", required = false) Long chippingLocationId,
                                  @RequestParam(name = "lifeStatus", required = false) LifeStatus lifeStatus,
                                  @RequestParam(name = "gender", required = false) Gender gender,
                                  @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) int from,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) int size) {
        return service.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size);
    }

    @GetMapping("/{id}")
    public AnimalDto getEntity(@PathVariable @NotNull @Positive Long id) {
        return service.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDto create(@Valid @RequestBody AnimalDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public AnimalDto update(@PathVariable @NotNull @Positive Long id,
                            @RequestBody @Valid AnimalUpdateRequest dto) {
        return service.updateEntity(id, dto);
    }


    @PostMapping("/{animalId}/types/{typeId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalDto addType(@PathVariable @NotNull @Positive Long animalId,
                             @PathVariable @NotNull @Positive Long typeId) {
        return service.addTypeToAnimal(animalId, typeId);
    }

    @DeleteMapping("/{animalId}/types/{typeId}")
    public AnimalDto deleteType(@PathVariable @NotNull @Positive Long animalId,
                                @PathVariable @NotNull @Positive Long typeId) {
        return service.deleteTypeFromAnimal(animalId, typeId);
    }

    @PutMapping("/{animalId}/types")
    public AnimalDto updateType(@PathVariable @NotNull @Positive Long animalId,
                                @RequestBody @Valid UpdateAnimalTypeDto updateAnimalTypeDto) {
        return service.updateAnimalType(animalId, updateAnimalTypeDto);
    }
}
