package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AnimalTypeDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.AnimalType;
import org.springframework.stereotype.Component;

@Component
public class AnimalTypeMapper extends AbstractMapper<AnimalType, AnimalTypeDto> {
    @Override
    public AnimalType createEntityFromDto(AnimalTypeDto dto) {
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
