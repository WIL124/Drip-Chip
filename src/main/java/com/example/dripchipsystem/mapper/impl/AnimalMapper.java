package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.model.AnimalType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public abstract class AnimalMapper extends AbstractMapper<Animal, AnimalDto> {
    @Override
    public abstract Animal fromDto(AnimalDto dto);

    @Override
    public abstract AnimalDto toDto(Animal dto);

    public abstract List<AnimalType> animalTypesId(List<Long> value);

    public AnimalType animalTypeId(Long value) {
        return new AnimalType();
    }
}
