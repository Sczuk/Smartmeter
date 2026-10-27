package com.registerService.application.service.device;

import com.registerService.application.useCases.device.CreateDevice;
import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.domain.exception.smartmeter.SmartmeterNotFoundException;
import com.registerService.domain.exception.user.UnauthorizedUserException;
import com.registerService.domain.model.Device;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.domain.repository.SmartmeterRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class CreateDeviceImpl implements CreateDevice {

    private final DeviceRepository deviceRepository;

    private final SmartmeterRepository smartmeterRepository;

    private final DeviceIntegration deviceIntegration;

    public CreateDeviceImpl(DeviceRepository repository, SmartmeterRepository smartmeterRepository, DeviceIntegration deviceIntegration) {
        this.deviceRepository = repository;
        this.smartmeterRepository = smartmeterRepository;
        this.deviceIntegration = deviceIntegration;
    }

    @Override
    public Device execute(Device device, String smartmeterId, String userId) {
        Smartmeter smartmeter = smartmeterRepository.findById(smartmeterId).orElseThrow(()-> new SmartmeterNotFoundException("Smartmeter by "+smartmeterId+" id  not found"));

        if (!smartmeter.getUser().getId().equals(UUID.fromString(userId))) {
            throw new UnauthorizedUserException("User not authorized to create device");
        }

        device.setCreated_at(new Timestamp(System.currentTimeMillis()));
        device.setSmartmeter(smartmeter);
        Device savedDevice = deviceRepository.save(device);

        if (!smartmeter.getDevices().isEmpty()) {
            if (smartmeter.getDevices().size() == 2) {
                savedDevice.setVoltagePin(3);
                savedDevice.setRelayPin(4);
                savedDevice.setCurrentPin(5);
            }
            if (smartmeter.getDevices().size() == 3) {
                savedDevice.setVoltagePin(6);
                savedDevice.setRelayPin(7);
                savedDevice.setCurrentPin(8);
            }
            if (smartmeter.getDevices().size() == 4) {
                savedDevice.setVoltagePin(9);
                savedDevice.setRelayPin(10);
                savedDevice.setCurrentPin(11);
            }
            if (smartmeter.getDevices().size() == 5) {
                savedDevice.setVoltagePin(12);
                savedDevice.setRelayPin(13);
                savedDevice.setCurrentPin(14);
            }
        } else {
            savedDevice.setVoltagePin(0);
            savedDevice.setRelayPin(1);
            savedDevice.setCurrentPin(2);
        }

        smartmeter.setDevices(List.of(savedDevice));
        deviceIntegration.deviceCreated(savedDevice, smartmeter.getId());
        smartmeterRepository.save(smartmeter);

        return device;
    }
}
