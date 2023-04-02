package com.example.dripchipsystem.common.mapper;

import com.example.dripchipsystem.common.dto.AbstractDto;
import com.example.dripchipsystem.common.model.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {
    E entityFromDto(D dto);

    D toDto(E dto);
    void updateEntityFromDto(E entity, D dto);
}
