package com.registerService.infra.integration.smartmeter;

import com.registerService.application.useCases.integration.SmartmeterIntegration;
import com.registerService.domain.model.Smartmeter;
import com.registerService.infra.integration.smartmeter.dto.SmartmeterIntegrationMapper;
import org.springframework.stereotype.Service;

@Service
public class SmartmeterIntegrationImpl implements SmartmeterIntegration {

    private final SmartmeterIntegrationMapper smartmeterIntegrationMapper;

    private final SmartmeterClient smartmeterClient;

    public SmartmeterIntegrationImpl(SmartmeterIntegrationMapper smartmeterIntegrationMapper, SmartmeterClient smartmeterClient) {
        this.smartmeterIntegrationMapper = smartmeterIntegrationMapper;
        this.smartmeterClient = smartmeterClient;
    }

    @Override
    public void smartmeterCreated(Smartmeter smartmeter) {
        smartmeterClient.smartmeterCreated(smartmeterIntegrationMapper.toSmartmeterCreatedResquest(smartmeter));
    }

    @Override
    public void smartmeterDeleted(Smartmeter smartmeter) {
        smartmeterClient.smartmeterDeleted(smartmeterIntegrationMapper.toSmartmeterDeletedRequest(smartmeter).getId());
    }
}
