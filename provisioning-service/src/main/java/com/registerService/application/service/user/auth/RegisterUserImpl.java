package com.registerService.application.service.user.auth;

import com.registerService.application.useCases.security.IdentityProvider;
import com.registerService.application.useCases.user.auth.RegisterUser;
import com.registerService.domain.exception.user.UserExistsException;
import com.registerService.domain.model.User;
import com.registerService.domain.model.enums.Role;
import com.registerService.domain.repository.UserRepository;

import java.sql.Timestamp;
import java.util.UUID;

public class RegisterUserImpl implements RegisterUser {

    private final UserRepository repository;

    private final IdentityProvider identityProvider;

    public RegisterUserImpl(UserRepository repository, IdentityProvider identityProvider) {
        this.repository = repository;
        this.identityProvider = identityProvider;
    }

    @Override
    public User execute(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UserExistsException("Email already registered");
        }

        User validUser = new User();

        validUser.setEmail(user.getEmail());
        validUser.setName(user.getName());
        validUser.setPassword(user.getPassword());
        validUser.setRole(Role.USER);
        validUser.setCreated_at(new Timestamp(System.currentTimeMillis()));

        UUID id = identityProvider.createUser(validUser);
        validUser.setId(id);
        validUser.setPassword(null);

        try {
            return repository.save(validUser);
        } catch (RuntimeException e) {
            identityProvider.deleteUser(id);
            throw e;
        }
    }

}