package energy_api.energy_iot_service.infra.web.measurements.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SumPowerWResponse {

    private double powerW;

    private Instant at;

}
