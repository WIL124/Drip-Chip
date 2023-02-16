package com.example.dripchipsystem.service;

import com.example.dripchipsystem.dto.AbstractDto;

public interface CommonService<DTO extends AbstractDto> {
    DTO getEntity(Long id);

    DTO create(DTO dto);

    DTO updateEntity(Long id, DTO dto);


    void delete(Long id);
}
