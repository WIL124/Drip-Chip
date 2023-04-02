package com.example.dripchipsystem.account.repository;

import com.example.dripchipsystem.account.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryCustom {
    List<Account> findByFilter(String firstName, String lastName, String email, int from, int size);
}
