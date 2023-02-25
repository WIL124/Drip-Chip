package com.example.dripchipsystem.service;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.mapper.Mapper;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.repo.CommonRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public abstract class AbstractService
        <ENTITY extends AbstractEntity, REPOSITORY extends CommonRepository<ENTITY>,
                MAPPER extends Mapper<ENTITY, DTO>, DTO extends AbstractDto>
        implements CommonService<DTO> {

    protected REPOSITORY repository;
    protected MAPPER mapper;

    public DTO getEntity(Long id) {
        return mapper.toDto(getEntityOrThrow(id));
    }


    @Override
    public DTO create(DTO dto) {
        ENTITY entity = mapper.entityFromDto(dto);
        try {
            repository.save(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        return mapper.toDto(entity);
    }

    public void delete(Long id) {
        ENTITY entity = getEntityOrThrow(id);
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.valueOf(400));
        }

    }

    @Override
    public DTO updateEntity(Long id, DTO dto) {
        ENTITY entity = getEntityOrThrow(id);
        mapper.updateEntityFromDto(entity, dto);
        try {
            return mapper.toDto(repository.save(entity));
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    protected ENTITY getEntityOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
