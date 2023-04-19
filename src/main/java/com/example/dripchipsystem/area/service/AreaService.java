package com.example.dripchipsystem.area.service;

import com.example.dripchipsystem.area.dto.AreaAnalytics;
import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.mapper.AreaMapper;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.repository.AreaRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@Service
public class AreaService extends AbstractService<Area, AreaRepository, AreaMapper, AreaDto> {
    private final AreaValidator areaValidator;
    private final AreaAnalyserFactory areaAnalyserFactory;

    public AreaService(AreaRepository repository,
                       AreaMapper mapper,
                       AreaValidator areaValidator,
                       AreaAnalyserFactory areaAnalyserFactory) {
        super(repository, mapper);
        this.areaValidator = areaValidator;
        this.areaAnalyserFactory = areaAnalyserFactory;
    }

    @Override
    public AreaDto create(AreaDto dto) {
        validateNewArea(dto);
        return super.create(dto);
    }

    @Override
    public AreaDto updateEntity(Long id, AreaDto dto) {
        validateExistArea(dto, getEntityOrThrow(id, repository));
        return super.updateEntity(id, dto);
    }

    public AreaAnalytics getAreaAnalytics(Long areaId, LocalDate startDate, LocalDate endDate) {
        validateDates(startDate, endDate);
        Area area = getEntityOrThrow(areaId, repository);
        return areaAnalyserFactory.createAnalyser(area, startDate, endDate)
                .analyse();
    }

    private void validateDates(LocalDate startDate, LocalDate endDate) throws ResponseStatusException {
        if (startDate != null && endDate != null) {
            if (endDate.isBefore(startDate)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    private void validateNewArea(AreaDto dto) throws ResponseStatusException {
        if (!areaValidator.isValidAreaPoints(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Area> areas = repository.findAll();
        if (areaValidator.hasIntersections(dto.getAreaPoints(), areas)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    private void validateExistArea(AreaDto dto, Area area) throws ResponseStatusException {
        if (!areaValidator.isValidAreaPoints(dto.getAreaPoints())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<Area> areas = repository.findAll();
        areas.remove(area);
        if (areaValidator.hasIntersections(dto.getAreaPoints(), areas)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
