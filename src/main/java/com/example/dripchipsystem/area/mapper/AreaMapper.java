package com.example.dripchipsystem.area.mapper;

import com.example.dripchipsystem.area.dto.AreaDto;
import com.example.dripchipsystem.area.model.Area;
import com.example.dripchipsystem.common.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper implements Mapper<Area, AreaDto> {
    @Override
    public Area entityFromDto(AreaDto dto) {
        return null;
    }

    @Override
    public AreaDto toDto(Area dto) {
        return null;
    }

    @Override
    public void updateEntityFromDto(Area entity, AreaDto dto) {

    }
}
