package com.registerService.application.useCases.device;

public interface DeleteDeviceById {

    void execute(String deviceId, String macAddress, String email);

}
