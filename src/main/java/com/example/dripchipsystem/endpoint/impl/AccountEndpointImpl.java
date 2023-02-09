package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.endpoint.AccountEndpoint;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountEndpointImpl implements AccountEndpoint {
    private AccountService service;

    @Override
    public Account getAccount(Integer accountId) {
        return service.getAccount(accountId);
    }

    @Override
    public Account update(AccountDto dto, Integer accountId) {
        return null;
    }

    @Override
    public List<Account> search(String firstName, String lastName, String email,
                                Integer from, Integer size) {
        return service.search(firstName, lastName, email, from, size);
    }
}
