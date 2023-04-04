package com.example.dripchipsystem.security;

import com.example.dripchipsystem.account.dto.AccountDto;
import com.example.dripchipsystem.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class SecurityMethods {
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
