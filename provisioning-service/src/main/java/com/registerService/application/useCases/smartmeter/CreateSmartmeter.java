package com.registerService.application.useCases.smartmeter;

import com.registerService.domain.model.Smartmeter;

public interface CreateSmartmeter {

    Smartmeter execute(Smartmeter smartmeter, String userId);

}
