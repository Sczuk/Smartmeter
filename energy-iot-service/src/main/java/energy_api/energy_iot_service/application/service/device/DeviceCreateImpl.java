package energy_api.energy_iot_service.application.service.device;

import energy_api.energy_iot_service.application.useCases.device.CreateDevice;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceConfigPublisher;
import energy_api.energy_iot_service.domain.exception.DeviceAlreadyExistsException;
import energy_api.energy_iot_service.domain.exception.SmartmeterNotFoundException;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;

public class DeviceCreateImpl implements CreateDevice {

    private final DeviceRepository deviceRepository;

    private final SmartmeterRepository smartmeterRepository;

    private final DeviceConfigPublisher deviceConfigPublisher;

    public DeviceCreateImpl(DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository, DeviceConfigPublisher deviceConfigPublisher) {
        this.deviceRepository = deviceRepository;
        this.smartmeterRepository = smartmeterRepository;
        this.deviceConfigPublisher = deviceConfigPublisher;
    }

    @Override
    public Device execute(Device device, String smartmeterId) {
        Smartmeter smartmeter = smartmeterRepository.findById(smartmeterId)
                .orElseThrow(()-> new SmartmeterNotFoundException("not possible add this device, smartmeter by "+smartmeterId+" not found"));

        if(deviceRepository.existsById(device.getId())){
            throw new DeviceAlreadyExistsException("This device already exists");
        }

        device.setSmartmeter(smartmeter);
        deviceConfigPublisher.execute(device);
        return deviceRepository.save(device);
    }
}
