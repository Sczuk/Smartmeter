package energy_api.energy_iot_service.infra.web.smartmeter.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SmartmeterCreatedRequest {

    private String id;

    private String userId;

    private String macAddress;

    private String name;

    private Timestamp timestamp;

}
