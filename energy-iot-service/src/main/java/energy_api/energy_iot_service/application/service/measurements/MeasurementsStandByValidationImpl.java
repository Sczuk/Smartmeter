package energy_api.energy_iot_service.application.service.measurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.MeasurementsStandByValidation;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceCommandPublisher;
import energy_api.energy_iot_service.domain.enums.DeviceCommand;
import energy_api.energy_iot_service.domain.enums.DeviceState;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;

import java.util.List;

public class MeasurementsStandByValidationImpl implements MeasurementsStandByValidation {

    private final DeviceCommandPublisher deviceCommandPublisher;
    private final DeviceRepository deviceRepository;

    public MeasurementsStandByValidationImpl(DeviceCommandPublisher deviceCommandPublisher, DeviceRepository deviceRepository) {
        this.deviceCommandPublisher = deviceCommandPublisher;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void execute(List<DeviceMeasurement> deviceMeasurements) {
        for (DeviceMeasurement deviceMeasurement : deviceMeasurements) {
            if (deviceMeasurement.getState().equals(DeviceState.OFFLINE) || deviceMeasurement.getState().equals(DeviceState.ONLINE)) {
                return;
            }
        }

        Device device = deviceRepository.findById(deviceMeasurements.getFirst().getDeviceId());
        deviceCommandPublisher.publishCommand(device, DeviceCommand.TURN_OFF);
    }
}