package com.example.dripchipsystem.repo;

import com.example.dripchipsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>, AccountRepositoryCustom {
}
