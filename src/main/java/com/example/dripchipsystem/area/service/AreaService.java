package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import org.springframework.stereotype.Service;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private AreaValidator areaValidator;
    public AreaService(AreaRepository repository, AreaMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public AreaDto create(AreaDto dto) {
        areaValidator.validateArea(dto.getAreaPoints());
        return super.create(dto);
    }
}
