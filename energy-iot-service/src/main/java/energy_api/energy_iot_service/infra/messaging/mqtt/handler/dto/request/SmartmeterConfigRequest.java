package energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartmeterConfigRequest {

    private String smartmeterId;
    private boolean active;

}
