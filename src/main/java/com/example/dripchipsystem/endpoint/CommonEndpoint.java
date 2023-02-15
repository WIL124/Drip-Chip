package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.model.AbstractEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public interface CommonEndpoint<E extends AbstractEntity> {
    Dto<E> getEntity(@PathVariable @NotNull @Min(1) Long id);

    Dto<E> create(@Valid Dto<E> dto);
}
