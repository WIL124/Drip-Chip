package com.example.dripchipsystem.endpoint;

import com.example.dripchipsystem.dto.AccountDto;
import com.example.dripchipsystem.model.Account;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/accounts")
@Validated
public interface AccountEndpoint {
    @PutMapping("/{accountId}")
    Account update(@RequestBody @Valid AccountDto dto,
                   @PathVariable(required = false) @NotNull @Min(1) Integer accountId);
}
