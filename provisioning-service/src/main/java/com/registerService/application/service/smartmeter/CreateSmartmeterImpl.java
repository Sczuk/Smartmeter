package com.registerService.application.service.smartmeter;

import com.registerService.application.useCases.integration.SmartmeterIntegration;
import com.registerService.application.useCases.smartmeter.CreateSmartmeter;
import com.registerService.domain.exception.user.UserNotFoundException;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.model.User;
import com.registerService.domain.repository.SmartmeterRepository;
import com.registerService.domain.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class CreateSmartmeterImpl implements CreateSmartmeter {

    private final UserRepository userRepository;
    private final SmartmeterRepository smartmeterRepository;
    private final SmartmeterIntegration smartmeterIntegration;


    public CreateSmartmeterImpl(UserRepository userRepository, SmartmeterRepository smartmeterRepository, SmartmeterIntegration smartmeterIntegration) {
        this.userRepository = userRepository;
        this.smartmeterRepository = smartmeterRepository;
        this.smartmeterIntegration = smartmeterIntegration;
    }

    @Override
    public Smartmeter execute(Smartmeter smartmeter, String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new UserNotFoundException("User not found by "+userId+" Id"));

        smartmeter.setCreated_at(new Timestamp(System.currentTimeMillis()));
        smartmeter.setUser(user);
        Smartmeter savedSmartmeter = smartmeterRepository.save(smartmeter);

        user.setSmartmeters(List.of(savedSmartmeter));
        userRepository.save(user);

        smartmeterIntegration.smartmeterCreated(savedSmartmeter);

        return savedSmartmeter;
    }


}
