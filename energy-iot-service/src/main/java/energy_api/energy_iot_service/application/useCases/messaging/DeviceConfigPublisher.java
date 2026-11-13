package energy_api.energy_iot_service.application.useCases.messaging;

import energy_api.energy_iot_service.domain.model.Device;

public interface DeviceConfigPublisher {

    void execute(Device device);

}
