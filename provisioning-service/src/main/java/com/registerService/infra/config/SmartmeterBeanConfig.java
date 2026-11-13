package com.registerService.infra.config;

import com.registerService.application.service.smartmeter.CreateSmartmeterImpl;
import com.registerService.application.service.smartmeter.DeleteSmartmeterImpl;
import com.registerService.application.service.smartmeter.FindSmartmeterImpl;
import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.application.useCases.integration.SmartmeterIntegration;
import com.registerService.application.useCases.smartmeter.CreateSmartmeter;
import com.registerService.application.useCases.smartmeter.DeleteSmartmeter;
import com.registerService.application.useCases.smartmeter.FindSmartmeter;
import com.registerService.domain.repository.DeviceRepository;
import com.registerService.domain.repository.SmartmeterRepository;
import com.registerService.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartmeterBeanConfig {

    @Bean
    public CreateSmartmeter createSmartmeter(UserRepository userRepository,
                                             SmartmeterRepository smartmeterRepository,
                                             SmartmeterIntegration smartmeterIntegration){
        return new CreateSmartmeterImpl(userRepository, smartmeterRepository, smartmeterIntegration);

    }

    @Bean
    public FindSmartmeter findSmartmeter(SmartmeterRepository repository){
        return new FindSmartmeterImpl(repository);
    }

    @Bean
    public DeleteSmartmeter deleteSmartmeter(SmartmeterRepository smartmeterRepository,
                                             DeviceRepository deviceRepository,
                                             SmartmeterIntegration smartmeterIntegration,
                                             DeviceIntegration deviceIntegration){
        return new DeleteSmartmeterImpl(smartmeterRepository,deviceRepository, smartmeterIntegration, deviceIntegration);
    }
}
