package com.example.dripchipsystem;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Loader implements ApplicationRunner {
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) {
        try {

        accountService.create(AccountDto.builder()
                .firstName("adminFirstName")
                .lastName("adminLastName")
                .email("admin@simbirsoft.com")
                .password("qwerty123")
                .role("ADMIN")
                .build());
        accountService.create(AccountDto.builder()
                .firstName("chipperFirstName")
                .lastName("chipperLastName")
                .email("chipper@simbirsoft.com")
                .password("qwerty123")
                .role("CHIPPER")
                .build());
        accountService.create(AccountDto.builder()
                .firstName("userFirstName")
                .lastName("userLastName")
                .email("user@simbirsoft.com")
                .password("qwerty123")
                .role("USER")
                .build());
        }catch (Exception e){

        }
    }
}
