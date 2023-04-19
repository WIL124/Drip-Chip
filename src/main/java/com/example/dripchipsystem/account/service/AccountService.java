package com.example.dripchipsystem.account.service;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.mapper.AccountMapper;
import com.example.dripchipsystem.account.model.Account;
import com.example.dripchipsystem.account.model.Role;
import com.example.dripchipsystem.account.repository.AccountRepository;
import com.example.dripchipsystem.common.service.AbstractService;
import com.example.dripchipsystem.common.service.CommonService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService
        extends AbstractService<Account, AccountRepository, AccountMapper, AccountDto>
        implements UserDetailsService, CommonService<AccountDto> {

    public AccountService(AccountRepository repository, AccountMapper accountMapper) {
        super(repository, accountMapper);
    }

    public AccountDto register(AccountDto accountDto) {
        accountDto.setRole(Role.USER.getName());
        return super.create(accountDto);
    }

    public List<AccountDto> search(String firstName, String lastName, String email, int from, int size) {
        return repository.findByFilter(firstName, lastName, email, from, size)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User by email " + email + " not found"));
    }

    @Override
    public void delete(Long id) {
        Account entity = getEntityOrThrow(id, repository);
        if (!entity.getChippedAnimals().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try {
            repository.delete(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }
}
