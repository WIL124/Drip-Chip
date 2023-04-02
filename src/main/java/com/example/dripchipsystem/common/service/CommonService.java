package com.example.dripchipsystem.common.service;

import com.example.dripchipsystem.common.dto.AbstractDto;

public interface CommonService<DTO extends AbstractDto> {
    DTO getEntity(Long id);

    DTO create(DTO dto);

    DTO updateEntity(Long id, DTO dto);

    void delete(Long id);
}