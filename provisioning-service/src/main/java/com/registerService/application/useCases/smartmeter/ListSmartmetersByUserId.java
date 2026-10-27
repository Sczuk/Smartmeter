package com.registerService.application.useCases.smartmeter;

import com.registerService.domain.model.Smartmeter;

import java.util.List;

public interface ListSmartmetersByUserId {

    List<Smartmeter> execute(String userId);

}
