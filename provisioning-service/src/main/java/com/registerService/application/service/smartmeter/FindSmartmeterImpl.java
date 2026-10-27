package com.registerService.application.service.smartmeter;

import com.registerService.application.useCases.smartmeter.FindSmartmeter;
import com.registerService.domain.exception.smartmeter.SmartmeterNotFoundException;
import com.registerService.domain.exception.user.UnauthorizedUserException;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.repository.SmartmeterRepository;

import java.util.UUID;

public class FindSmartmeterImpl implements FindSmartmeter {

    private final SmartmeterRepository smartmeterRepository;

    public FindSmartmeterImpl(SmartmeterRepository smartmeterRepository) {
        this.smartmeterRepository = smartmeterRepository;
    }

    @Override
    public Smartmeter execute(String smartmeterId, String userId) {
        Smartmeter smartmeter = smartmeterRepository.findById(smartmeterId).orElseThrow(() -> new SmartmeterNotFoundException("smartmeter by " + smartmeterId + " address not found"));
        if (smartmeter.getUser().getId().equals(UUID.fromString(userId))){
            return smartmeter;
        }
        throw new UnauthorizedUserException("user not authorized to find this smartmeter");
    }


}
