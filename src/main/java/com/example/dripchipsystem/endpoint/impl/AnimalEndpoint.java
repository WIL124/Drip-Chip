package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.UpdateAnimalTypeDto;
import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.service.impl.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
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
    public List<AnimalDto> search(@RequestParam(name = "startDateTime", required = false) LocalDateTime startDateTime,
                                  @RequestParam(name = "endDateTime", required = false) LocalDateTime endDateTime,
                                  @RequestParam(name = "chipperId", required = false) Integer chipperId,
                                  @RequestParam(name = "chippingLocationId", required = false) Long chippingLocationId,
                                  @RequestParam(name = "lifeStatus", required = false) String lifeStatus,
                                  @RequestParam(name = "gender", required = false) String gender,
                                  @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) int from,
                                  @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) int size) {
        return service.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size);
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
