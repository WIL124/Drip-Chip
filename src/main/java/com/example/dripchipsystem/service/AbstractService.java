package com.example.dripchipsystem.service;

import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.model.AbstractEntity;
import com.example.dripchipsystem.repo.CommonRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {
    protected R repository;

    public E getEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public E create(Dto dto) {
        return null;
    }
}
