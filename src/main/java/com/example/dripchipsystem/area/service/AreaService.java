package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.model.AreaPoint;
import com.example.dripchipsystem.area.repository.AreaPointRepository;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


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
    @Transactional
    public AreaDto create(AreaDto dto) {
        if (!areaValidator.validateArea(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Area area = mapper.entityFromDto(dto);
        repository.save(area);
        Long areaId = area.getId();
        List<AreaPoint> areaPointList = dto.getAreaPoints().stream()
                .map(pointDto -> new AreaPoint(pointDto.getLongitude(), pointDto.getLatitude(), areaId))
                .toList();
        areaPointRepository.saveAll(areaPointList);
        area = repository.findById(area.getId()).orElseThrow();
        return mapper.toDto(area);
    }
}
