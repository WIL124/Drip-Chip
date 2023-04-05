package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaPointRepository;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import org.springframework.stereotype.Service;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private final AreaValidator areaValidator;
    private final AreaPointRepository areaPointRepository;

    public AreaService(AreaRepository repository, AreaMapper mapper, AreaValidator areaValidator, AreaPointRepository areaPointRepository) {
        super(repository, mapper);
        this.areaValidator = areaValidator;
        this.areaPointRepository = areaPointRepository;
    }

    @Override
    public AreaDto create(AreaDto dto) {
        areaValidator.validateArea(dto.getAreaPoints());
//        areaPointRepository.saveAll(mapper.entityFromDto(dto).getAreaPoints());
        return super.create(dto);
    }
}
