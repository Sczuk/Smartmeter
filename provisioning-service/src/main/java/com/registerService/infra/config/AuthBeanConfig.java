package com.registerService.infra.config;

import com.registerService.application.service.user.auth.LoginUserImpl;
import com.registerService.application.service.user.auth.RegisterUserImpl;
import com.registerService.application.useCases.security.IdentityProvider;
import com.registerService.application.useCases.user.auth.LoginUser;
import com.registerService.application.useCases.user.auth.RegisterUser;
import com.registerService.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfig {

    @Bean
    public RegisterUser registerUser(UserRepository userRepository, IdentityProvider identityProvider) {
        return new RegisterUserImpl(userRepository, identityProvider);
    }

    @Bean
    public LoginUser loginUser(IdentityProvider identityProvider){
        return new LoginUserImpl(identityProvider);
    }
}
