package energy_api.energy_iot_service.infra.web.smartmeter.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartmeterCreatedResponse {

    private String name;

    private String userId;

}
