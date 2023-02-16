package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AbstractDto;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CommonEndpoint<DTO extends AbstractDto> {
    DTO getEntity(@PathVariable @NotNull @Min(1) Long id);

    DTO create(@Valid DTO dto);
}
