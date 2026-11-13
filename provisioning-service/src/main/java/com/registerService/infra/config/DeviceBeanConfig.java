package com.registerService.infra.config;

import com.registerService.application.service.device.CreateDeviceImpl;
import com.registerService.application.service.device.DeleteDeviceByIdImpl;
import com.registerService.application.useCases.device.CreateDevice;
import com.registerService.application.useCases.device.DeleteDeviceById;
import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.domain.repository.SmartmeterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceBeanConfig {

    @Bean
    public CreateDevice createDevice(DeviceRepository deviceRepository,
                                     SmartmeterRepository smartmeterRepository,
                                     DeviceIntegration deviceIntegration){
        return new CreateDeviceImpl(deviceRepository, smartmeterRepository, deviceIntegration);
    }

    @Bean
    public DeleteDeviceById deleteDeviceByName(DeviceRepository deviceRepository,
                                               SmartmeterRepository smartmeterRepository,
                                               DeviceIntegration deviceIntegration){
        return new DeleteDeviceByIdImpl(deviceRepository, smartmeterRepository, deviceIntegration);
    }
}
