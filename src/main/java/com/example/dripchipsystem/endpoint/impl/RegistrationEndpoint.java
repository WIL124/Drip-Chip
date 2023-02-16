package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AccountDto;
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
    public AccountDto registration(@RequestBody @Valid AccountDto registerDto) {
        return accountService.register(registerDto);
    }
}
