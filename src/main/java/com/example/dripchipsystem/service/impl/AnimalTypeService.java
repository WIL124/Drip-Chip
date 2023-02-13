package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.model.AnimalType;
import com.example.dripchipsystem.repo.AnimalTypeRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService extends AbstractService<AnimalType, AnimalTypeRepository> {
    public AnimalTypeService(AnimalTypeRepository repository) {
        super(repository);
    }
}
