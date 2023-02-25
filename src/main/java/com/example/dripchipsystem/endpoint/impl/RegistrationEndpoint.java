package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.service.impl.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationEndpoint extends AbstractEndpoint<AccountService, AccountDto> {
    public RegistrationEndpoint(AccountService service) {
        super(service);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto registration(@RequestBody @Valid AccountDto registerDto) {
        return service.create(registerDto);
    }
}
