package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.AccountDto;
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
    public Account getAccount(int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return account.get();
    }

    @Override
    public List<Account> search(String firstName, String lastName, String email, int from, int size) {
        return accountRepository.findByFilter(firstName, lastName, email, from, size);
    }

    @Override
    public Account register(AccountDto accountDto) {
        Account account = toEntity(accountDto);
        return accountRepository.save(account);
    }

    private Account toEntity(AccountDto registerDto) {
        return Account.builder()
                .firstName(registerDto.getFirstname())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .build();
    }
}
