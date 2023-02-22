package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AbstractDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CommonEndpoint<DTO extends AbstractDto> {
    @GetMapping("/{id}")
    DTO getEntity(@PathVariable @NotNull @DecimalMin(value = "0", inclusive = false) Long id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DTO create(@Valid @RequestBody DTO dto);

    @PutMapping("/{id}")
    DTO update(@PathVariable @NotNull @DecimalMin(value = "0", inclusive = false) Long id, @RequestBody @Valid DTO dto);

    @DeleteMapping("/{id}")
    void delete(@PathVariable @NotNull @DecimalMin(value = "0", inclusive = false) Long id);
}
