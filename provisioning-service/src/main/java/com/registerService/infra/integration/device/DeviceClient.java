package com.registerService.infra.integration.device;

import com.registerService.infra.integration.device.dto.DeviceCreatedRequest;
import com.registerService.infra.integration.device.dto.DeviceDeletedRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/energy-iot/devices")
public interface DeviceClient {

    @PostExchange("/smartmeter/{smartmeterId}/deviceCreated")
    void deviceCreated(@RequestBody DeviceCreatedRequest device, @PathVariable String smartmeterId);

    @DeleteExchange("/deviceDeleted/{deviceId}")
    void deviceDeleted(@PathVariable String deviceId);

}
