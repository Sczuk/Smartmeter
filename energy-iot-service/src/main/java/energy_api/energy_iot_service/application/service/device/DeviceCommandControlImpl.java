package energy_api.energy_iot_service.application.service.device;

import energy_api.energy_iot_service.application.useCases.device.DeviceCommandControl;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceCommandPublisher;
import energy_api.energy_iot_service.domain.enums.DeviceCommand;
import energy_api.energy_iot_service.domain.enums.DeviceState;
import energy_api.energy_iot_service.domain.exception.UnauthorizedCommandException;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;

public class DeviceCommandControlImpl implements DeviceCommandControl {

    private final DeviceCommandPublisher deviceCommandPublisher;
    private final DeviceRepository deviceRepository;
    private final DeviceMeasurementRepository deviceMeasurementRepository;

    public DeviceCommandControlImpl(DeviceCommandPublisher deviceCommandPublisher, DeviceRepository deviceRepository, DeviceMeasurementRepository deviceMeasurementRepository) {
        this.deviceCommandPublisher = deviceCommandPublisher;
        this.deviceRepository = deviceRepository;
        this.deviceMeasurementRepository = deviceMeasurementRepository;
    }

    @Override
    public void turnOff(String deviceId, String userId) {
        Device device = deviceRepository.findById(deviceId);

        if(!device.getSmartmeter().getUserId().equals(userId)){
            throw new UnauthorizedCommandException("command not authorized");
        }

        if(deviceMeasurementRepository.findFirstByMetadata_DeviceIdOrderByMeasuredDesc(device.getId()).getState().equals(DeviceState.OFFLINE)){
            throw new UnauthorizedCommandException("The device is already offline");
        }

        deviceCommandPublisher.publishCommand(device, DeviceCommand.TURN_OFF);
    }

    @Override
    public void turnOn(String deviceId, String userId) {
        Device device = deviceRepository.findById(deviceId);

        if(!device.getSmartmeter().getUserId().equals(userId)){
            throw new UnauthorizedCommandException("command not authorized");
        }

        if(deviceMeasurementRepository.findFirstByMetadata_DeviceIdOrderByMeasuredDesc(device.getId()).getState().equals(DeviceState.ONLINE)){
            throw new UnauthorizedCommandException("The device is already online");
        }

        deviceCommandPublisher.publishCommand(device, DeviceCommand.TURN_ON);
    }
}
