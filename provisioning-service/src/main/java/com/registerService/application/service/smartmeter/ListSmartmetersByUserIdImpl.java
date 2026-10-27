package com.registerService.application.service.smartmeter;

import com.registerService.application.useCases.smartmeter.ListSmartmetersByUserId;
import com.registerService.domain.exception.user.UserNotFoundException;
import com.registerService.domain.model.Smartmeter;
import com.registerService.domain.model.User;
import com.registerService.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListSmartmetersByUserIdImpl implements ListSmartmetersByUserId {

    private final UserRepository userRepository;

    public ListSmartmetersByUserIdImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Smartmeter> execute(String userId) {
        User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(
                ()-> new UserNotFoundException("id "+userId+" not found"));

        if(user.getSmartmeters().isEmpty()){
            Smartmeter smartmeter = new Smartmeter();
            smartmeter.setUser(user);
            List<Smartmeter> listSmartmeter = new ArrayList<>();
            listSmartmeter.add(smartmeter);
            return listSmartmeter;
        }

        return user.getSmartmeters();
    }
}
