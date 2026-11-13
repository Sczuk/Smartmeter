package energy_api.energy_iot_service.infra.messaging.mqtt.handler;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.smartmeter.ActivateSmartmeter;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request.SmartmeterConfigRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SmartmeterConfigHandler {

    private final ActivateSmartmeter activateSmartmeter;

    public SmartmeterConfigHandler( ActivateSmartmeter activateSmartmeter) {
        this.activateSmartmeter = activateSmartmeter;
    }

    @ServiceActivator(inputChannel = "smartmeterConfig")
    public void handler(SmartmeterConfigRequest request) throws JsonProcessingException {
        activateSmartmeter.execute(request.getSmartmeterId(), request.isActive());
    }
}
