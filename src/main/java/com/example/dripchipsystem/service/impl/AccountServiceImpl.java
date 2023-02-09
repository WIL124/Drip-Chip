package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(Integer id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return account.get();
    }

    @Override
    public List<Account> search(String firstName, String lastName, String email, Integer from, Integer size) {
        return accountRepository.findByFilter(firstName, lastName, email, from, size);
    }
}
