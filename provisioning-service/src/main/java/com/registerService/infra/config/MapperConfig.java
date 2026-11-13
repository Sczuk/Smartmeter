package com.registerService.infra.config;

import com.registerService.infra.integration.device.dto.DeviceIntegrationMapper;
import com.registerService.infra.integration.smartmeter.dto.SmartmeterIntegrationMapper;
import com.registerService.infra.persistence.device.DeviceJpaMapper;
import com.registerService.infra.persistence.smartmeter.SmartmeterJpaMapper;
import com.registerService.infra.persistence.user.UserJpaMapper;
import com.registerService.infra.web.user.auth.dto.AuthUserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserJpaMapper userJpaMapper(){
        return Mappers.getMapper(UserJpaMapper.class);
    }

    @Bean
    public DeviceJpaMapper deviceJpaMapper(){
        return Mappers.getMapper(DeviceJpaMapper.class);
    }

    @Bean
    public SmartmeterJpaMapper smartmeterJpaMapper(){
        return Mappers.getMapper(SmartmeterJpaMapper.class);
    }

    @Bean
    public AuthUserMapper authUserMapper(){
        return Mappers.getMapper(AuthUserMapper.class);
    }

    @Bean
    public com.registerService.infra.web.smartmeter.dto.SmartmeterMapper smartmeterMapper(){
        return Mappers.getMapper(com.registerService.infra.web.smartmeter.dto.SmartmeterMapper.class);
    }

    @Bean
    public com.registerService.infra.web.device.DeviceMapper deviceMapper(){
        return Mappers.getMapper(com.registerService.infra.web.device.DeviceMapper.class);
    }

    @Bean
    public DeviceIntegrationMapper deviceEventMapper(){return Mappers.getMapper(DeviceIntegrationMapper.class);}

    @Bean
    public SmartmeterIntegrationMapper smartmeterEventMapper(){return Mappers.getMapper(SmartmeterIntegrationMapper.class);}

    @Bean
    public DeviceIntegrationMapper deviceIntegrationMapper(){return Mappers.getMapper(DeviceIntegrationMapper.class);}
}
