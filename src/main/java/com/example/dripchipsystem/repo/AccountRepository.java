package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CommonRepository<Account>, AccountRepositoryCustom {
    Optional<Account> findByEmail(String email);
}
