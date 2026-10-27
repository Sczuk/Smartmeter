package com.registerService.application.useCases.security;

public record AuthTokens(
        String access_token,
        String refreshToken,
        long expiresIn,
        long refreshExpiresIn,
        String tokenType
) {}