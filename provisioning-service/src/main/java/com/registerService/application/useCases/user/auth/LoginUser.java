package com.registerService.application.useCases.user.auth;

import com.registerService.application.useCases.security.AuthTokens;
import com.registerService.domain.model.User;

public interface LoginUser {

    AuthTokens execute(User user);

}
