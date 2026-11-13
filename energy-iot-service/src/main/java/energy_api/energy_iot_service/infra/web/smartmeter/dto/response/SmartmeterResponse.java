package energy_api.energy_iot_service.infra.web.smartmeter.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartmeterResponse {

    private String name;

    private boolean active;

    private String id;

}
