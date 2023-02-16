package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.impl.AnimalDto;
import com.example.dripchipsystem.mapper.impl.AnimalMapper;
import com.example.dripchipsystem.model.Animal;
import com.example.dripchipsystem.repo.AnimalRepository;
import com.example.dripchipsystem.service.AbstractService;
import com.example.dripchipsystem.service.CommonService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService
        extends AbstractService<Animal, AnimalRepository, AnimalMapper, AnimalDto>
    implements CommonService<AnimalDto> {
    public AnimalService(AnimalRepository repository, AnimalMapper animalMapper) {
        super(repository, animalMapper);
    }

    public List<AnimalDto> search(LocalDateTime startDateTime, LocalDateTime endDateTime,
                               Integer chipperId, Long chippingLocationId,
                               String lifeStatus, String gender, int from, int size) {
        return repository.search(startDateTime, endDateTime, chipperId, chippingLocationId, lifeStatus, gender, from, size)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateEntityFromDto(Animal entity, AnimalDto dto) {

    }

    @Override
    public AnimalDto updateEntity(Long id, AnimalDto dto) {
        return null;
    }
}
