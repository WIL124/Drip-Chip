package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationEndpoint {
    private AccountService accountService;

    @PostMapping
    public Account registration(AccountDto registerDto) {
        return accountService.register(registerDto);
    }
}
