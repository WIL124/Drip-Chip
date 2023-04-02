package com.example.dripchipsystem.animalType.service;

import com.example.dripchipsystem.animalType.dto.AnimalTypeDto;
import com.example.dripchipsystem.animalType.mapper.AnimalTypeMapper;
import com.example.dripchipsystem.animalType.model.AnimalType;
import com.example.dripchipsystem.animalType.repository.AnimalTypeRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.common.service.CommonService;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService
        extends AbstractService<AnimalType, AnimalTypeRepository, AnimalTypeMapper, AnimalTypeDto>
        implements CommonService<AnimalTypeDto> {
    public AnimalTypeService(AnimalTypeRepository repository, AnimalTypeMapper animalTypeMapper) {
        super(repository, animalTypeMapper);
    }
}
