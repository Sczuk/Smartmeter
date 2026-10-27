package com.registerService.infra.persistence.user;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    @EntityGraph(attributePaths = {"smartmeters"})
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
