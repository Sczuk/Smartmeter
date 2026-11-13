package com.registerService.infra.integration.device.dto;

import com.registerService.domain.model.Device;
import org.mapstruct.Mapper;import org.mapstruct.Mapping;import org.mapstruct.Mappings;

@Mapper
public interface DeviceIntegrationMapper {

    DeviceCreatedRequest toDeviceCreatedRequest(Device device);

    DeviceDeletedRequest toDeviceDeletedRequest(Device device);



}
