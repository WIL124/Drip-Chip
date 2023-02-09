package com.example.dripchipsystem.service;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    Account getAccount(int id);

    List<Account> search(String firstName, String lastName, String email, int from, int size);

    Account register(AccountDto registerDto);
}
