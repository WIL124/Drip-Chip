package com.example.dripchipsystem.mapper.impl;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.mapper.AbstractMapper;
import com.example.dripchipsystem.model.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public abstract class AccountMapper extends AbstractMapper<Account, AccountDto> {

    @Override
    public abstract Account fromDto(AccountDto dto);

    @Override
    public abstract AccountDto toDto(Account entity);
}
