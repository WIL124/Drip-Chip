package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.service.CommonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Validated
public abstract class AbstractEndpoint<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonEndpoint<E> {
    protected S service;

    @GetMapping("/{id}")
    public Dto<E> getEntity(@PathVariable @NotNull @Min(1) Long id) {
        E entity = service.getEntity(id);
        return toDto(entity);
    }
    @PostMapping
    public Dto<E> create(@Valid Dto<E> dto){
        E entity = service.create(dto);
        return toDto(entity);
    }
    protected abstract Dto<E> toDto(E entity);
}
