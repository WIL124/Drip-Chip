package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.dto.Dto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.service.impl.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountEndpoint extends AbstractEndpoint<Account, AccountService> {
    public AccountEndpoint(AccountService service) {
        super(service);
    }

    @GetMapping("/search")
    List<Account> search(@RequestParam(name = "firstName", required = false) String firstName,
                         @RequestParam(name = "lastName", required = false) String lastName,
                         @RequestParam(name = "email", required = false) String email,
                         @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                         @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {
        return service.search(firstName, lastName, email, from, size);
    }

    @Override
    protected Dto<Account> toDto(Account entity) {
        return AccountDto.builder()
                .id(entity.getId())
                .firstname(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .build();
    }
}
