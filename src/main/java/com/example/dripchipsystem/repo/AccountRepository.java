package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CommonRepository<Account>, AccountRepositoryCustom {
}
