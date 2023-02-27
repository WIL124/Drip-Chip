package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.Account;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;

@Component
@AllArgsConstructor
public class AccountMapper extends AbstractMapper<Account, AccountDto> {
    private PasswordEncoder passwordEncoder;

    @Override
    public Account entityFromDto(AccountDto dto) {
        return Account.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
    }

    @Override
    public AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(null)
                .build();
    }

    @Override
    public void updateEntityFromDto(Account entity, AccountDto dto) {
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
    }
}
