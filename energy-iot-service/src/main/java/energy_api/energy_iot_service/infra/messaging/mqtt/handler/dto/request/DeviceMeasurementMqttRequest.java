package energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request;

import energy_api.energy_iot_service.domain.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceMeasurementMqttRequest {

    private String deviceId;

    private DeviceState relayState;

    private double current_a;

    private double voltage_v;

    private double power_w;

}
