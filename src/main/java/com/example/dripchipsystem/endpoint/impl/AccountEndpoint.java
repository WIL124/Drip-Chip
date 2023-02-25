package com.example.dripchipsystem.endpoint.impl;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.endpoint.AbstractEndpoint;
import com.example.dripchipsystem.model.Account;
import com.example.dripchipsystem.service.impl.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountEndpoint
        extends AbstractEndpoint<AccountService, AccountDto> {
    public AccountEndpoint(AccountService service) {
        super(service);
    }

    @GetMapping("/search")
    List<AccountDto> search(@RequestParam(name = "firstName", required = false) String firstName,
                            @RequestParam(name = "lastName", required = false) String lastName,
                            @RequestParam(name = "email", required = false) String email,
                            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
                            @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {
        return service.search(firstName, lastName, email, from, size);
    }

    @GetMapping("/{id}")
    public AccountDto getEntity(@PathVariable @NotNull @Positive Long id) {
        return service.getEntity(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @NotNull @Positive Long id) {
        service.delete(id);
    }
    @PutMapping("/{id}")
    public AccountDto update(@PathVariable @NotNull @Positive Long id,
                      @RequestBody @Valid AccountDto dto) {
        return service.updateEntity(id, dto);
    }
}
