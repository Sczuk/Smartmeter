package energy_api.energy_iot_service.application.useCases.device;

import energy_api.energy_iot_service.domain.model.Device;

public interface CreateDevice {

    Device execute(Device device, String smartmeterId);

}
