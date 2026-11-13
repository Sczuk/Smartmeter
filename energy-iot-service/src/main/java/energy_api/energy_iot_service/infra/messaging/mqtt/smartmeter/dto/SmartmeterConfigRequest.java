package energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SmartmeterConfigRequest {

    private String smartmeterId;

}
