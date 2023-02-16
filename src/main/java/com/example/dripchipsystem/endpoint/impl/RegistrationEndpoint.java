package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AbstractDto;
import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationEndpoint {
    private AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AbstractDto registration(@RequestBody @Valid AccountDto registerDto) {
        return accountService.create(registerDto);
    }
}
