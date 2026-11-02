package energy_api.energy_iot_service.application.useCases.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;

import java.util.List;

public interface ListLatestMeasurements {

    List<DeviceMeasurement> execute(String smartmeterId, String userId);

}
