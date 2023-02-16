package com.example.dripchipsystem.mapper;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.model.AbstractEntity;

public abstract class AbstractMapper <E extends AbstractEntity,D extends AbstractDto> implements Mapper<E, D>{
}
