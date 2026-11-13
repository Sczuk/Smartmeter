package energy_api.energy_iot_service.application.useCases.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;

import java.util.List;

public interface ConsumptionDaily {

    List<DeviceMeasurementDailySummary> execute(String deviceId, String smartmeterId, String userId, String from, String to);


}
