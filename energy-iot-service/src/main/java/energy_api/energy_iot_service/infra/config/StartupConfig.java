package energy_api.energy_iot_service.infra.config;

import energy_api.energy_iot_service.application.useCases.messaging.SubscribeAllSmartmeterConfigTopic;
import energy_api.energy_iot_service.application.useCases.messaging.SubscribeAllSmartmeterMeasurementsTopic;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig {

    private final SubscribeAllSmartmeterMeasurementsTopic subscribeAllSmartmeterMeasurementsTopic;
    private final SubscribeAllSmartmeterConfigTopic subscribeAllSmartmeterConfigTopic;

    public StartupConfig(SubscribeAllSmartmeterMeasurementsTopic subscribeAllSmartmeterMeasurementsTopic, SubscribeAllSmartmeterConfigTopic subscribeAllSmartmeterConfigTopic) {
        this.subscribeAllSmartmeterMeasurementsTopic = subscribeAllSmartmeterMeasurementsTopic;
        this.subscribeAllSmartmeterConfigTopic = subscribeAllSmartmeterConfigTopic;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void executarAoIniciar() {
        subscribeAllSmartmeterMeasurementsTopic.execute();
        subscribeAllSmartmeterConfigTopic.execute();
    }

}
