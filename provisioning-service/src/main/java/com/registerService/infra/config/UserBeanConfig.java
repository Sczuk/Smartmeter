package com.registerService.infra.config;

import com.registerService.application.service.smartmeter.ListSmartmetersByUserIdImpl;
import com.registerService.application.useCases.smartmeter.ListSmartmetersByUserId;
import com.registerService.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    public ListSmartmetersByUserId listSmartmetersByEmail(UserRepository userRepository){
        return new ListSmartmetersByUserIdImpl(userRepository);
    }

}
