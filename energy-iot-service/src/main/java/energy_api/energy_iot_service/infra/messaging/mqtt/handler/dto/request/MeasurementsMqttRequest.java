package energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class MeasurementsMqttRequest {

    private String smartmeterId;

    private List<DeviceMeasurementMqttRequest> devices = new ArrayList<>();

}
