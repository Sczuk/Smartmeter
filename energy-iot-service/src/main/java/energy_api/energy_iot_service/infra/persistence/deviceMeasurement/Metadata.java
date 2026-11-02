package energy_api.energy_iot_service.infra.persistence.deviceMeasurement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Getter
@Setter
public class Metadata {

    @Field("device_id")
    private String deviceId;

    @Field("smartmeter_id")
    private String smartmeterId;

}
