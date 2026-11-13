package com.registerService.domain.repository;

import com.registerService.domain.model.Smartmeter;

import java.util.Optional;
import java.util.UUID;

public interface SmartmeterRepository {

    Smartmeter save(Smartmeter smartmeter);

    Optional<Smartmeter> findByMacAddress(String macAddress);

    void deleteByMacAddress(String macAddress);

    void deleteById(String smartmeterId);

    Optional<Smartmeter> findById(String id);

    Smartmeter findByNameAndUser_id(String name, UUID userId);

}
