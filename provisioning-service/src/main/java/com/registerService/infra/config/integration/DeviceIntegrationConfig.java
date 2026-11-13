package com.registerService.infra.config.integration;

import com.registerService.application.useCases.integration.DeviceIntegration;
import com.registerService.infra.integration.device.DeviceClient;
import com.registerService.infra.integration.device.DeviceIntegrationImpl;
import com.registerService.infra.integration.device.dto.DeviceIntegrationMapper;
import com.registerService.infra.security.keycloak.ServiceTokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class DeviceIntegrationConfig {

    @Value("${energy-iot.url}")
    private String energyIotUrl;

    @Bean
    public DeviceClient deviceClient(ServiceTokenInterceptor serviceTokenInterceptor){
        RestClient restClient = RestClient.builder()
                .baseUrl(energyIotUrl)
                .requestInterceptor(serviceTokenInterceptor)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(DeviceClient.class);
    }

    @Bean
    public DeviceIntegration deviceIntegration(DeviceIntegrationMapper deviceIntegrationMapper,
                                               DeviceClient deviceClient){
        return new DeviceIntegrationImpl(deviceIntegrationMapper, deviceClient);
    }
}
