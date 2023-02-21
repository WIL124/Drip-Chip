package com.example.dripchipsystem;

import com.example.dripchipsystem.dto.impl.AccountDto;
import com.example.dripchipsystem.service.impl.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
@AllArgsConstructor
public class UserSecurity {
    private AccountService accountService;
    public boolean hasUserId(Authentication authentication, Long userId) {
        String email = authentication.getName();
        AccountDto dto = accountService.getEntity(userId);
        return email.equals(dto.getEmail());
    }
}
