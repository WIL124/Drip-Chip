package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.AnimalTypeDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.endpoint.CommonEndpoint;
import com.example.dripchipsystem.service.impl.AnimalTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/animals/types")
public class AnimalTypeEndpoint
        extends AbstractEndpoint<AnimalTypeService, AnimalTypeDto>
        implements CommonEndpoint<AnimalTypeDto> {
    public AnimalTypeEndpoint(AnimalTypeService service) {
        super(service);
    }
}
