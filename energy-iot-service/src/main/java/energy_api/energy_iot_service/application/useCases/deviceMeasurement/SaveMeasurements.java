package energy_api.energy_iot_service.application.useCases.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;

public interface SaveMeasurements{

    void execute(DeviceMeasurement measurement);

}
