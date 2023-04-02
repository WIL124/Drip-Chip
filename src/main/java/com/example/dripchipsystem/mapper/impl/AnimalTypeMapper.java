package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.childs.AnimalTypeDto;
import com.example.dripchipsystem.mapper.Mapper;
import com.example.dripchipsystem.model.AnimalType;
import org.springframework.stereotype.Component;

@Component
public class AnimalTypeMapper implements Mapper<AnimalType, AnimalTypeDto> {
    @Override
    public AnimalType entityFromDto(AnimalTypeDto dto) {
        return new AnimalType(dto.getType());
    }

    @Override
    public AnimalTypeDto toDto(AnimalType entity) {
        return new AnimalTypeDto(entity.getId(), entity.getType());
    }

    @Override
    public void updateEntityFromDto(AnimalType entity, AnimalTypeDto dto) {
        entity.setType(dto.getType());
    }
}
