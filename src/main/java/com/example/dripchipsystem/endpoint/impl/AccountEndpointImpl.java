package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.endpoint.AccountEndpoint;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountEndpointImpl implements AccountEndpoint {
    private AccountService service;
    @Override
    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Integer accountId) {
        return service.getAccount(accountId);
    }

    @Override
    @GetMapping("/search")
    public List<Account> search(@RequestParam(name = "firstName", required = false) String firstName,
                                @RequestParam(name = "lastName", required = false) String lastName,
                                @RequestParam(name = "email", required = false) String email,
                                @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return service.search(firstName, lastName, email, from, size);
    }
}
