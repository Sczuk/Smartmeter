package energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter;

import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.application.useCases.messaging.SubscribeAllSmartmeterConfigTopic;
import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubscribeAllSmartmeterConfigTopicImpl implements SubscribeAllSmartmeterConfigTopic {

    private final SmartmeterRepository repository;

    private final SmartmeterSubscribeTopic subscribeTopic;

    public SubscribeAllSmartmeterConfigTopicImpl(SmartmeterRepository repository, SmartmeterSubscribeTopic subscribeTopic) {
        this.repository = repository;
        this.subscribeTopic = subscribeTopic;
    }

    @Override
    public void execute() {
        List<Smartmeter> smartmeters = repository.findByActive(false);
        if(smartmeters.isEmpty())return;
        smartmeters.parallelStream().forEach(smartmeter -> subscribeTopic.subscribeToSmartmeterConfigTopic(smartmeter.getId()));
    }

}
