package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private final AreaValidator areaValidator;

    public AreaService(AreaRepository repository, AreaMapper mapper, AreaValidator areaValidator) {
        super(repository, mapper);
        this.areaValidator = areaValidator;
    }

    @Override
    public AreaDto create(AreaDto dto) {
        if (!areaValidator.isValidAreaPoints(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return super.create(dto);
    }
}
