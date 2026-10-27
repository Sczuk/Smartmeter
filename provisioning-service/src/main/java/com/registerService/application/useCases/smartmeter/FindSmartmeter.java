package com.registerService.application.useCases.smartmeter;

import com.registerService.domain.model.Smartmeter;

public interface FindSmartmeter {

    Smartmeter execute(String smartmeterId, String email);

}
