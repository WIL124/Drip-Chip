package com.example.dripchipsystem.account.repository;

import com.example.dripchipsystem.common.repository.CommonRepository;
import com.example.dripchipsystem.account.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CommonRepository<Account>, AccountRepositoryCustom {
    Optional<Account> findByEmail(String email);
}
