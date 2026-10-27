package com.registerService.application.useCases.integration;

import com.registerService.domain.model.Device;

public interface DeviceIntegration {

    void deviceCreated(Device device, String smartmeterId);
    void deviceDeleted(Device device);

}
