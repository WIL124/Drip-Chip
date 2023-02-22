package com.example.dripchipsystem.mapper;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {
    E entityFromDto(D dto);

    D toDto(E dto);
    void updateEntityFromDto(E entity, D dto);
}
