package energy_api.energy_iot_service.infra.messaging.mqtt.device.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeviceConfigRequest {

    private String deviceId;

    private int relayPin;

    private int currentPin;

    private int voltagePin;
}
