package com.example.dripchipsystem.area.mapper;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.dto.AreaPointDto;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.area.model.AreaPoint;
import com.example.dripchipsystem.common.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AreaMapper implements Mapper<Area, AreaDto> {
    @Override
    public Area entityFromDto(AreaDto dto) {
        return Area.builder()
                .name(dto.getName())
                .areaPoints(dto.getAreaPoints().stream()
                        .map(areaPoint ->
                                AreaPoint.builder()
                                        .x(areaPoint.getX())
                                        .y(areaPoint.getY())
                                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public AreaDto toDto(Area area) {
        return AreaDto.builder()
                .id(area.getId())
                .name(area.getName())
                .areaPoints(area.getAreaPoints().stream()
                        .map(areaPoint -> new AreaPointDto(areaPoint.getX(), areaPoint.getY()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void updateEntityFromDto(Area entity, AreaDto dto) {

    }
}
