package energy_api.energy_iot_service.infra.config.beanConfig;

import energy_api.energy_iot_service.application.service.smartmeter.ActivateSmartmeterImpl;
import energy_api.energy_iot_service.application.service.smartmeter.CreateSmartmeterImpl;
import energy_api.energy_iot_service.application.service.smartmeter.DeleteSmartmeterImpl;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterConfigPublisher;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.application.useCases.smartmeter.ActivateSmartmeter;
import energy_api.energy_iot_service.application.useCases.smartmeter.CreateSmartmeter;
import energy_api.energy_iot_service.application.useCases.smartmeter.DeleteSmartmeter;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartmeterBeanConfig {

    @Bean
    public CreateSmartmeter createSmartmeter(SmartmeterRepository repository, SmartmeterSubscribeTopic subscribeTopic, SmartmeterConfigPublisher publisher){
        return new CreateSmartmeterImpl(repository, subscribeTopic, publisher);
    }

    @Bean
    public DeleteSmartmeter deleteSmartmeter(SmartmeterRepository smartmeterRepository, DeviceRepository deviceRepository){
        return new DeleteSmartmeterImpl(smartmeterRepository, deviceRepository);
    }

    @Bean
    public ActivateSmartmeter activateSmartmeter(SmartmeterRepository repository, SmartmeterSubscribeTopic subscribeTopic){
        return new ActivateSmartmeterImpl(repository, subscribeTopic);
    }

}
