package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.model.Account;

import java.util.List;

public interface AccountEndpoint {
    Account getAccount(Integer id);
    List<Account> search(String firstName, String lastName, String email, Integer from, Integer size);
}
