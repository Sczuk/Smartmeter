package com.registerService.infra.web.user.auth.dto.response;

import com.registerService.application.useCases.security.AuthTokens;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthUserResponse {

    private String access_token;

    private String refresh_token;

    private long expires_in;

    private long refresh_expires_in;

    private String token_type;

    public static AuthUserResponse from(AuthTokens tokens) {
        AuthUserResponse response = new AuthUserResponse();
        response.setAccess_token(tokens.access_token());
        response.setRefresh_token(tokens.refreshToken());
        response.setExpires_in(tokens.expiresIn());
        response.setRefresh_expires_in(tokens.refreshExpiresIn());
        response.setToken_type(tokens.tokenType());
        return response;
    }
}