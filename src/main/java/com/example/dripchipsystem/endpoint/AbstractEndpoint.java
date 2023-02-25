package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.service.CommonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Validated
public abstract class AbstractEndpoint<SERVICE extends CommonService<DTO>, DTO extends AbstractDto> {
    protected SERVICE service;
}
