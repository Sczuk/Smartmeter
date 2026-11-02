package energy_api.energy_iot_service.application.service.smartmeter;

import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.application.useCases.smartmeter.ActivateSmartmeter;
import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import energy_api.energy_iot_service.infra.persistence.exception.SmartmeterNotFoundException;

public class ActivateSmartmeterImpl implements ActivateSmartmeter {

    private final SmartmeterRepository repository;
    private final SmartmeterSubscribeTopic subscribeTopic;

    public ActivateSmartmeterImpl(SmartmeterRepository repository, SmartmeterSubscribeTopic subscribeTopic) {
        this.repository = repository;
        this.subscribeTopic = subscribeTopic;
    }

    @Override
    public boolean execute(String smartmeterId, boolean updateState) {
        Smartmeter smartmeter = repository.findById(smartmeterId)
                .orElseThrow(() -> new SmartmeterNotFoundException("not possible to update state of smartmeter"));

        smartmeter.setActive(updateState);
        repository.save(smartmeter);

        subscribeTopic.subscribeToMeasurementsTopic(smartmeterId);

        return smartmeter.isActive();
    }

}
