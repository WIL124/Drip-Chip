package com.example.dripchipsystem.auth;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.common.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.account.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationEndpoint extends AbstractEndpoint<AccountService, AccountDto> {
    public RegistrationEndpoint(AccountService service) {
        super(service);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto registration(@RequestBody @Valid AccountDto registerDto) {
        return service.create(registerDto);
    }
}
