package energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request.DeviceMeasurementMqttRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface MeasurementMqttMapper {

    @Mappings({
            @Mapping(target = "smartmeterId", ignore = true),
            @Mapping(target = "measured", ignore = true),
            @Mapping(source = "voltage_v", target = "voltageV"),
            @Mapping(source = "current_a", target = "currentA"),
            @Mapping(source = "power_w", target = "powerW"),
            @Mapping(source = "relayState", target = "state")
    })
    DeviceMeasurement toDomain(DeviceMeasurementMqttRequest request);

}
