package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.AnimalTypeDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.service.impl.AnimalTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/animals/types")
@Validated
public class AnimalTypeEndpoint
        extends AbstractEndpoint<AnimalTypeService, AnimalTypeDto> {
    public AnimalTypeEndpoint(AnimalTypeService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public AnimalTypeDto getEntity(@PathVariable @NotNull @Positive Long id) {
        return service.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalTypeDto create(@Valid @RequestBody AnimalTypeDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public AnimalTypeDto update(@PathVariable @NotNull @Positive Long id,
                                @RequestBody @Valid AnimalTypeDto dto) {
        return service.updateEntity(id, dto);

    }
}
