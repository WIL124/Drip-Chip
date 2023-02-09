package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;

import java.util.List;

public interface AccountRepositoryCustom {
    List<Account> findByFilter(String firstName, String lastName, String email, int from, int size);
}
