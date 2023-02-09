package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@Validated
public interface AccountEndpoint {
    @GetMapping("/{accountId}")
    Account getAccount(@PathVariable(required = false) @NotNull @Min(1) Integer accountId);

    @PutMapping("/{accountId}")
    Account update(@RequestBody @Valid AccountDto dto,
                   @PathVariable(required = false) @NotNull @Min(1) Integer accountId);
    @GetMapping("/search")
    List<Account> search(@RequestParam(name = "firstName", required = false) String firstName,
                         @RequestParam(name = "lastName", required = false) String lastName,
                         @RequestParam(name = "email", required = false) String email,
                         @RequestParam(name = "from", required = false, defaultValue = "0") @NotNull @Min(0) Integer from,
                         @RequestParam(name = "size", required = false, defaultValue = "10") @NotNull @Min(1) Integer size);
}
