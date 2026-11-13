package energy_api.energy_iot_service.infra.web.measurements.dto;

import energy_api.energy_iot_service.domain.enums.DeviceState;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DeviceMeasurementsResponse {

    private String deviceId;

    private String smartmeterId;

    private double voltageV;

    private double currentA;

    private double powerW;

    private DeviceState state;

    private Instant measured;

}
