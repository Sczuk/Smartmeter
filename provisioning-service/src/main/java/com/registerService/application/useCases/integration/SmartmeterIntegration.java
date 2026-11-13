package com.registerService.application.useCases.integration;

import com.registerService.domain.model.Smartmeter;

public interface SmartmeterIntegration {

    void smartmeterCreated(Smartmeter smartmeter);
    void smartmeterDeleted(Smartmeter smartmeter);

}
