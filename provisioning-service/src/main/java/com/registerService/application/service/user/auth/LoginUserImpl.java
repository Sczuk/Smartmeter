package com.registerService.application.service.user.auth;

import com.registerService.application.useCases.security.AuthTokens;
import com.registerService.application.useCases.security.IdentityProvider;
import com.registerService.application.useCases.user.auth.LoginUser;
import com.registerService.domain.model.User;

public class LoginUserImpl implements LoginUser {

    private final IdentityProvider identityProvider;

    public LoginUserImpl(IdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
    }

    @Override
    public AuthTokens execute(User user) {
        return identityProvider.login(user.getEmail(), user.getPassword());
    }
}