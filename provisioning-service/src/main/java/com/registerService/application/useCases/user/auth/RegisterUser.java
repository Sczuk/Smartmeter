package com.registerService.application.useCases.user.auth;

import com.registerService.domain.model.User;

public interface RegisterUser {

    User execute(User user);

}
