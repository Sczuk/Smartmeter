package com.registerService.domain.repository;

import com.registerService.domain.model.Device;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository {

    Device save(Device device);

    void deleteById(String id);

    Optional<Device> findById(String id);


}
