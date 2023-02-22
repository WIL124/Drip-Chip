package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.mapper.impl.AccountMapper;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.service.AbstractService;
import com.example.dripchipsystem.service.CommonService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService
        extends AbstractService<Account, AccountRepository, AccountMapper, AccountDto>
        implements UserDetailsService, CommonService<AccountDto> {

    public AccountService(AccountRepository repository, AccountMapper accountMapper) {
        super(repository, accountMapper);
    }


    public List<AccountDto> search(String firstName, String lastName, String email, int from, int size) {
        return repository.findByFilter(firstName, lastName, email, from, size).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = repository.findByEmail(email);
        if (account.isEmpty()) throw new UsernameNotFoundException("User by email " + email + " not found");
        return account.get();
    }
}
