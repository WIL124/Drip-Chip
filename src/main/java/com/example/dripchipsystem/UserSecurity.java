package com.example.dripchipsystem;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

@Component("userSecurity")
@AllArgsConstructor
@Validated
public class UserSecurity {
    private AccountService accountService;

    public boolean hasUserId(Authentication authentication, Long userId) {
        if (userId <= 0) return true;
        String email = authentication.getName();
        AccountDto dto;
        try {
            dto = accountService.getEntity(userId);
        } catch (ResponseStatusException ex) {
            return false;
        }
        return email.equals(dto.getEmail());
    }
}
