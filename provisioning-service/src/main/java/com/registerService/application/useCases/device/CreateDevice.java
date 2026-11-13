package com.registerService.application.useCases.device;

import com.registerService.domain.model.Device;

public interface CreateDevice {

    Device execute(Device device, String macAddress, String email);

}
