package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AnimalTypeDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.AnimalType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AnimalTypeMapper extends AbstractMapper<AnimalType, AnimalTypeDto> {
    @Override
    public abstract AnimalType fromDto(AnimalTypeDto dto);

    @Override
    public abstract AnimalTypeDto toDto(AnimalType dto);
}
