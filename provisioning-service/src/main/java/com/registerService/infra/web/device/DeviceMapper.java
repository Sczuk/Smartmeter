package com.registerService.infra.web.device;

import com.registerService.domain.model.Device;
import com.registerService.domain.model.Smartmeter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DeviceMapper {

    @Mappings({
            @Mapping(source = "smartmeter.name", target = "smartmeterName"),
            @Mapping(source = "name", target = "deviceName"),
            @Mapping(target = "channels", expression = "java(setAmountChannels(domain))")
    })
    DeviceResponse toResponse(Device domain);

    Device toDomain(DeviceRequest request);

    default int[] setAmountChannels(Device device){
        return new int[] { device.getVoltagePin(), device.getRelayPin(), device.getCurrentPin()};
    }
}
