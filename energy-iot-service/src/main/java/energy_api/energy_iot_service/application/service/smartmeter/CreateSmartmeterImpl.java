package energy_api.energy_iot_service.application.service.smartmeter;

import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterConfigPublisher;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.application.useCases.smartmeter.CreateSmartmeter;
import energy_api.energy_iot_service.domain.exception.SmartmeterAlreadyExistsException;
import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;

public class CreateSmartmeterImpl implements CreateSmartmeter {

    private final SmartmeterRepository repository;
    private final SmartmeterSubscribeTopic subscribeTopic;
    private final SmartmeterConfigPublisher configPublisher;

    public CreateSmartmeterImpl(SmartmeterRepository repository, SmartmeterSubscribeTopic subscribeTopic, SmartmeterConfigPublisher configPublisher) {
        this.repository = repository;
        this.subscribeTopic = subscribeTopic;
        this.configPublisher = configPublisher;
    }

    @Override
    public Smartmeter execute(Smartmeter smartmeter) {

        if(repository.existsById(smartmeter.getId())){
            throw new SmartmeterAlreadyExistsException("Not possible create this smartmeter");
        }

        smartmeter.setActive(false);

        subscribeTopic.subscribeToSmartmeterConfigTopic(smartmeter.getMacAddress());
        configPublisher.execute(smartmeter.getId(), smartmeter.getMacAddress());

        return repository.save(smartmeter);
    }

}
