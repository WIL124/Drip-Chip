package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.impl.AnimalTypeDto;
import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.mapper.impl.AnimalTypeMapper;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.model.AnimalType;
import com.example.dripchipsystem.repo.AnimalTypeRepository;
import com.example.dripchipsystem.service.AbstractService;
import com.example.dripchipsystem.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService
        extends AbstractService<AnimalType, AnimalTypeRepository, AnimalTypeMapper, AnimalTypeDto>
        implements CommonService<AnimalTypeDto> {
    public AnimalTypeService(AnimalTypeRepository repository, AnimalTypeMapper animalTypeMapper) {
        super(repository, animalTypeMapper);
    }

    @Override
    public void updateEntityFromDto(AnimalType entity, AnimalTypeDto dto) {

    }

    @Override
    public AnimalTypeDto updateEntity(Long id, AnimalTypeDto dto) {
        return null;
    }
}
