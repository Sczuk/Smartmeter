package energy_api.energy_iot_service.infra.web.device.dto;

import energy_api.energy_iot_service.domain.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceCreatedRequest {

    private String id;

    private DeviceType type;

    private String name;

    private int relayPin;

    private int currentPin;

    private int voltagePin;

}
