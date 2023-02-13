package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService extends AbstractService<Account, AccountRepository> {

    public AccountService(AccountRepository repository) {
        super(repository);
    }

    public List<Account> search(String firstName, String lastName, String email, int from, int size) {
        return repository.findByFilter(firstName, lastName, email, from, size);
    }

    public Account create(AccountDto accountDto) {
        Account account = toEntity(accountDto);
        return repository.save(account);
    }

    private Account toEntity(AccountDto registerDto) {
        return Account.builder()
                .firstName(registerDto.getFirstname())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .build();
    }
}
