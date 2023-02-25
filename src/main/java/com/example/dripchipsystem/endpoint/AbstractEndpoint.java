package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.service.CommonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Validated
public abstract class AbstractEndpoint<SERVICE extends CommonService<DTO>, DTO extends AbstractDto>
        implements CommonEndpoint<DTO> {
    protected SERVICE service;

    @GetMapping("/{id}")
    public DTO getEntity(@PathVariable @NotNull @Positive Long id) {
        return service.getEntity(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DTO create(@Valid @RequestBody DTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public DTO update(@PathVariable @NotNull @Positive Long id,
                      @RequestBody @Valid DTO dto) {
        return service.updateEntity(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
    }
}
