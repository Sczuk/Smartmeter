package com.registerService.application.useCases.security;

public record AuthTokens(
        String access_token,
        String refresh_token,
        long expires_in,
        long refresh_expires_in,
        String token_type
) {}