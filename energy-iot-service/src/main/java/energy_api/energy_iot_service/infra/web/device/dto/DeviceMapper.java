package energy_api.energy_iot_service.infra.web.device.dto;

import energy_api.energy_iot_service.domain.model.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeviceMapper {

    @Mapping(source = "smartmeter.macAddress", target = "smartmeterMacAddress")
    DeviceCreatedResponse toDeviceCreatedResponse(Device device);

    Device toDomain(DeviceCreatedRequest request);
}
