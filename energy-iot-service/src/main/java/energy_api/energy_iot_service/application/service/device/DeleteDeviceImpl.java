package energy_api.energy_iot_service.application.service.device;

import energy_api.energy_iot_service.application.useCases.device.DeleteDevice;
import energy_api.energy_iot_service.domain.exception.DeviceNotFoundException;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;

public class DeleteDeviceImpl implements DeleteDevice {

    private final DeviceRepository repository;

    public DeleteDeviceImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String id) {

        if(!repository.existsById(id)){
            throw new DeviceNotFoundException("not possible delete this device");
        }

        repository.deleteById(id);
    }

}
