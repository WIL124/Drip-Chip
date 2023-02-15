package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AnimalTypeDto;
import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.AnimalType;
import com.example.dripchipsystem.service.impl.AnimalTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals/types")
public class AnimalTypeEndpoint extends AbstractEndpoint<AnimalType, AnimalTypeService> {
    public AnimalTypeEndpoint(AnimalTypeService service) {
        super(service);
    }

    @Override
    protected Dto<AnimalType> toDto(AnimalType entity) {
        return AnimalTypeDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .build();
    }
}
