package com.registerService.application.service.device;

import com.registerService.application.useCases.device.DeleteDeviceById;
import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.domain.exception.device.DeviceNotFoundException;
import com.registerService.domain.exception.smartmeter.SmartmeterNotFoundException;
import com.registerService.domain.exception.user.UnauthorizedUserException;
import com.registerService.domain.model.Device;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.domain.repository.SmartmeterRepository;

public class DeleteDeviceByIdImpl implements DeleteDeviceById {

    private final DeviceRepository deviceRepository;

    private final SmartmeterRepository smartmeterRepository;

    private final DeviceIntegration deviceIntegration;

    public DeleteDeviceByIdImpl(DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository, DeviceIntegration deviceIntegration) {
        this.deviceRepository = deviceRepository;
        this.smartmeterRepository = smartmeterRepository;
        this.deviceIntegration = deviceIntegration;
    }

    @Override
    public void execute(String deviceId, String smartmeterId, String email) {
        Smartmeter smartmeter = smartmeterRepository.findById(smartmeterId).orElseThrow(() -> new SmartmeterNotFoundException("smartmeter by " + smartmeterId + " Id not found"));
        if(!smartmeter.getUser().getEmail().equals(email)){
            throw new UnauthorizedUserException("User not authorized to delete this device");
        }
        for(Device devices : smartmeter.getDevices()){
            if(devices.getId().equals(deviceId)){
                deviceRepository.deleteById(deviceId);
                deviceIntegration.deviceDeleted(devices);
            }
        }

        throw new DeviceNotFoundException("Device by "+deviceId+" name not found");
    }
}
