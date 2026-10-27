package com.registerService.infra.integration.device;

import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.domain.model.Device;
import com.registerService.infra.integration.device.dto.DeviceCreatedRequest;
import com.registerService.infra.integration.device.dto.DeviceDeletedRequest;
import com.registerService.infra.integration.device.dto.DeviceIntegrationMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceIntegrationImpl implements DeviceIntegration {

    private final DeviceIntegrationMapper deviceIntegrationMapper;

    private final DeviceClient deviceClient;

    public DeviceIntegrationImpl(DeviceIntegrationMapper deviceIntegrationMapper, DeviceClient deviceClient) {
        this.deviceIntegrationMapper = deviceIntegrationMapper;
        this.deviceClient = deviceClient;
    }

    @Override
    public void deviceCreated(Device device, String smartmeterId) {
        DeviceCreatedRequest request = deviceIntegrationMapper.toDeviceCreatedRequest(device);
        deviceClient.deviceCreated(request, smartmeterId);
    }

    @Override
    public void deviceDeleted(Device device) {
        deviceClient.deviceDeleted(deviceIntegrationMapper.toDeviceDeletedRequest(device).getId());
    }
}
