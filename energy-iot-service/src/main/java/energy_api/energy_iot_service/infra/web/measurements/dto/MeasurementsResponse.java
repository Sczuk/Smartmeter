package energy_api.energy_iot_service.infra.web.measurements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeasurementsResponse {

    private List<DeviceMeasurementsResponse> measurements;

}
