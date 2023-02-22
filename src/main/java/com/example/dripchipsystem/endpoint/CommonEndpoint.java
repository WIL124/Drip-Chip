package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AbstractDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CommonEndpoint<DTO extends AbstractDto> {
    DTO getEntity(@PathVariable @NotNull @DecimalMin(value = "0", inclusive = false) Long id);

    DTO create(@Valid @RequestBody DTO dto);
}
