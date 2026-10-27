package com.registerService.application.useCases.security;

import com.registerService.domain.model.User;

import java.util.UUID;

public interface IdentityProvider {

    UUID createUser(User user);

    void deleteUser(UUID id);

    AuthTokens refresh(String refreshToken);

    void logout(String refreshToken);

    AuthTokens login(String email, String password);
}