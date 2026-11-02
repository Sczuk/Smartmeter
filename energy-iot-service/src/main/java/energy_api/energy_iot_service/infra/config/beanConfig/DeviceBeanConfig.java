package energy_api.energy_iot_service.infra.config.beanConfig;


import energy_api.energy_iot_service.application.service.device.DeleteDeviceImpl;
import energy_api.energy_iot_service.application.service.device.DeviceCommandControlImpl;
import energy_api.energy_iot_service.application.service.device.DeviceCreateImpl;
import energy_api.energy_iot_service.application.useCases.device.CreateDevice;
import energy_api.energy_iot_service.application.useCases.device.DeleteDevice;
import energy_api.energy_iot_service.application.useCases.device.DeviceCommandControl;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceCommandPublisher;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceConfigPublisher;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceBeanConfig {

    @Bean
    public CreateDevice createDevice(DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository, DeviceConfigPublisher configPublisher){
        return new DeviceCreateImpl(deviceRepository, smartmeterRepository, configPublisher);
    }

    @Bean
    public DeleteDevice deleteDevice(DeviceRepository repository){
        return new DeleteDeviceImpl(repository);
    }

    @Bean
    public DeviceCommandControl deviceCommandControl(DeviceCommandPublisher deviceCommandPublisher, DeviceRepository deviceRepository, DeviceMeasurementRepository deviceMeasurementRepository){return new DeviceCommandControlImpl(deviceCommandPublisher, deviceRepository, deviceMeasurementRepository);}

}

