package energy_api.energy_iot_service.infra.messaging.mqtt.device.dto;

import energy_api.energy_iot_service.domain.enums.DeviceCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceCommandRequest {

    private String deviceId;

    private DeviceCommand command;
}
