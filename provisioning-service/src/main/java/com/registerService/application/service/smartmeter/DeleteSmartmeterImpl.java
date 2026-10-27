package com.registerService.application.service.smartmeter;

import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.application.useCases.integration.SmartmeterIntegration;
import com.registerService.application.useCases.smartmeter.DeleteSmartmeter;
import com.registerService.domain.exception.smartmeter.SmartmeterNotFoundException;
import com.registerService.domain.exception.user.UnauthorizedUserException;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.domain.repository.SmartmeterRepository;

import java.util.UUID;

public class DeleteSmartmeterImpl implements DeleteSmartmeter {

    private final SmartmeterRepository smartmeterRepository;
    private final DeviceRepository deviceRepository;
    private final SmartmeterIntegration smartmeterIntegration;
    private final DeviceIntegration deviceIntegration;

    public DeleteSmartmeterImpl(SmartmeterRepository smartmeterRepository, DeviceRepository deviceRepository, SmartmeterIntegration smartmeterIntegration, DeviceIntegration deviceIntegration) {
        this.smartmeterRepository = smartmeterRepository;
        this.deviceRepository = deviceRepository;
        this.smartmeterIntegration = smartmeterIntegration;
        this.deviceIntegration = deviceIntegration;
    }

    @Override
    public void execute(String smartmeterId, String userId) {
        Smartmeter smartmeter = smartmeterRepository.findById(smartmeterId).orElseThrow(()->new SmartmeterNotFoundException("Smartmeter not found"));
        if(!smartmeter.getUser().getId().equals(UUID.fromString(userId))) throw new UnauthorizedUserException("Not possible delete Smartmeter");

        smartmeter.getDevices().forEach(device -> {
            deviceRepository.deleteById(device.getId());
            deviceIntegration.deviceDeleted(device);
        });

        smartmeterIntegration.smartmeterDeleted(smartmeter);
        smartmeterRepository.deleteById(smartmeterId);
    }
}
