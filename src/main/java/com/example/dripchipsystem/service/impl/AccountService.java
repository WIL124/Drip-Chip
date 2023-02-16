package com.example.dripchipsystem.service.impl;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.repo.AccountRepository;
import com.example.dripchipsystem.service.AbstractService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends AbstractService<Account, AccountRepository> implements UserDetailsService {
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    public List<Account> search(String firstName, String lastName, String email, int from, int size) {
        return repository.findByFilter(firstName, lastName, email, from, size);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = repository.findByEmail(email);
        if (account.isEmpty()) throw new UsernameNotFoundException("User by email " + email + " not found");
        return account.get();
    }

    public AccountDto register(AccountDto registerDto) {
        Account account;
        try {
            account = repository.save(fromDto(registerDto));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(409));
        }
        registerDto.setId(account.getId());
        registerDto.setPassword(null);
        return registerDto;
    }

    public Account fromDto(AccountDto dto) {
        return Account.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }
}
