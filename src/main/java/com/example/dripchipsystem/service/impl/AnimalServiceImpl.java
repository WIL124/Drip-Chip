package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {
    @Override
    public Animal getAnimal(long id) {
        return null;
    }
}
