package energy_api.energy_iot_service.infra.web.device.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceCreatedResponse {

    private String name;

    private String smartmeterMacAddress;

}
