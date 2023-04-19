package com.example.dripchipsystem;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DefaultAccountLoader implements ApplicationRunner {
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {
        AccountDto adminDto = AccountDto.builder()
                .firstName("adminFirstName")
                .lastName("adminLastName")
                .email("admin@simbirsoft.com")
                .password("qwerty123")
                .role("ADMIN")
                .build();
        AccountDto chipperDto = AccountDto.builder()
                .firstName("chipperFirstName")
                .lastName("chipperLastName")
                .email("chipper@simbirsoft.com")
                .password("qwerty123")
                .role("CHIPPER")
                .build();
        AccountDto userDto = AccountDto.builder()
                .firstName("userFirstName")
                .lastName("userLastName")
                .email("user@simbirsoft.com")
                .password("qwerty123")
                .role("USER")
                .build();
        List<AccountDto> defaultAccounts = List.of(adminDto, chipperDto, userDto);
        for (AccountDto accountDto : defaultAccounts) {
            try {
                accountService.loadUserByUsername(accountDto.getEmail());
            } catch (UsernameNotFoundException e) {
                accountService.create(accountDto);
            }
        }
    }
}
