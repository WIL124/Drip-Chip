package com.example.dripchipsystem.account.mapper;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.model.Account;
import com.example.dripchipsystem.account.model.Role;
import com.example.dripchipsystem.common.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountMapper implements Mapper<Account, AccountDto> {
    private PasswordEncoder passwordEncoder;

    @Override
    public Account entityFromDto(AccountDto dto) {
        return Account.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole() == null ? Role.USER : Role.valueOf(dto.getRole()))
                .build();
    }

    @Override
    public AccountDto toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .role(entity.getRole().getName())
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
