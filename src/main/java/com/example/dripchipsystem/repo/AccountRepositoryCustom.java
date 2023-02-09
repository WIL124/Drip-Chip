package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepositoryCustom {
    List<Account> findByFilter(String firstName, String lastName, String email, int from, int size);
}
