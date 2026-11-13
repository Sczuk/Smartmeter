package com.registerService.infra.persistence.user;

import com.registerService.domain.exception.user.UserNotFoundException;
import com.registerService.domain.model.User;
import com.registerService.domain.repository.UserRepository;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    private final UserJpaMapper mapper;

    public UserRepositoryImpl(UserJpaRepository repository, UserJpaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        UserEntity userEntity = repository.save(mapper.toEntity(user,context));
        return mapper.toDomain(userEntity,context);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        UserEntity userEntity = repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("user by " + email + " email not found"));
        return Optional.ofNullable(mapper.toDomain(userEntity,context));
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        UserEntity userEntity = repository.findById(id).orElseThrow(() -> new UserNotFoundException("user by "+id+ "Id not found" ));
        return Optional.ofNullable(mapper.toDomain(userEntity,context));
    }
}
