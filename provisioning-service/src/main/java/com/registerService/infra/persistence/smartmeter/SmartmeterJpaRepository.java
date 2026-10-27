package com.registerService.infra.persistence.smartmeter;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SmartmeterJpaRepository extends JpaRepository<SmartmeterEntity, String> {

    @EntityGraph(attributePaths = {"devices"})
    Optional<SmartmeterEntity> findByMacAddress(String macAddress);

    void deleteByMacAddress(String macAddress);

    Optional<SmartmeterEntity> findByNameAndUser_id(String name, UUID userId);
}
