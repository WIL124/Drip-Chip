package com.example.dripchipsystem.service;

import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.model.AbstractEntity;

public interface CommonService<E extends AbstractEntity> {
    E getEntity(Long id);

    E create(Dto dto);
}
