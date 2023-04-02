package com.example.dripchipsystem;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.model.Account;
import com.example.dripchipsystem.account.model.Role;
import com.example.dripchipsystem.account.repository.AccountRepository;
import com.example.dripchipsystem.account.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class Loader implements ApplicationRunner {
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role admin = new Role("ADMIN");
        Role chipper = new Role("CHIPPER");
        Role user = new Role("USER");
        List<Role> roles = List.of(admin, chipper, user);
        roleRepository.saveAll(roles);
        accountRepository.save(Account.builder()
                .firstName("adminFirstName")
                .lastName("adminLastName")
                .email("admin@simbirsoft.com")
                .password("qwerty123")
                .roles(List.of(admin))
                .build());
        accountRepository.save(Account.builder()
                .firstName("chipperFirstName")
                .lastName("chipperLastName")
                .email("chipper@simbirsoft.com")
                .password("qwerty123")
                .roles(List.of(chipper))
                .build());
        accountRepository.save(Account.builder()
                .firstName("userFirstName")
                .lastName("userLastName")
                .email("user@simbirsoft.com")
                .password("qwerty123")
                .roles(List.of(user))
                .build());
    }
}
