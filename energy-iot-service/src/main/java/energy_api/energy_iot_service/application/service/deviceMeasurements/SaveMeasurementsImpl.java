package energy_api.energy_iot_service.application.service.deviceMeasurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SaveMeasurements;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;

public class SaveMeasurementsImpl implements SaveMeasurements {

    private final DeviceMeasurementRepository repository;

    public SaveMeasurementsImpl(DeviceMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeviceMeasurement measurement) {
        repository.save(measurement);
    }

}
