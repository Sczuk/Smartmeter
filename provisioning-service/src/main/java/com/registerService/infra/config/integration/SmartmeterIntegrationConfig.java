package com.registerService.infra.config.integration;

import com.registerService.application.useCases.integration.SmartmeterIntegration;
import com.registerService.infra.integration.smartmeter.SmartmeterClient;
import com.registerService.infra.integration.smartmeter.SmartmeterIntegrationImpl;
import com.registerService.infra.integration.smartmeter.dto.SmartmeterIntegrationMapper;
import com.registerService.infra.security.keycloak.ServiceTokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SmartmeterIntegrationConfig {

    @Value("${energy-iot.url}")
    private String energyIotUrl;

    @Bean
    public SmartmeterClient smartmeterClient(ServiceTokenInterceptor serviceTokenInterceptor){
        RestClient restClient = RestClient.builder()
                .baseUrl(energyIotUrl)
                .requestInterceptor(serviceTokenInterceptor)
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SmartmeterClient.class);
    }

    @Bean
    public SmartmeterIntegration smartmeterIntegration(SmartmeterClient smartmeterClient,
                                                   SmartmeterIntegrationMapper smartmeterIntegrationMapper){
        return new SmartmeterIntegrationImpl(smartmeterIntegrationMapper, smartmeterClient);
    }

}
