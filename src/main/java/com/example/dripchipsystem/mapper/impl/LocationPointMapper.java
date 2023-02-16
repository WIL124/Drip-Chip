package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.LocationPointDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.LocationPoint;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class LocationPointMapper extends AbstractMapper<LocationPoint, LocationPointDto> {
    @Override
    public abstract LocationPoint fromDto(LocationPointDto dto);

    @Override
    public abstract LocationPointDto toDto(LocationPoint dto);
}
