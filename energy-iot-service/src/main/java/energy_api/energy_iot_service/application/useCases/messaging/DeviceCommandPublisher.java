package energy_api.energy_iot_service.application.useCases.messaging;

import energy_api.energy_iot_service.domain.enums.DeviceCommand;
import energy_api.energy_iot_service.domain.model.Device;

public interface DeviceCommandPublisher {

    void publishCommand(Device device, DeviceCommand command);

}
