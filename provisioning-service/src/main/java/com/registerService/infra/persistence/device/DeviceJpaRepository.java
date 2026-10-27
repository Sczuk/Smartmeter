package com.registerService.infra.persistence.device;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceJpaRepository extends JpaRepository<DeviceEntity, String> {

}
