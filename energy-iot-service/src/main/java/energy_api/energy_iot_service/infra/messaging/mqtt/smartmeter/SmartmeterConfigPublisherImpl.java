package energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterConfigPublisher;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttGateway;
import energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter.dto.SmartmeterConfigRequest;


public class SmartmeterConfigPublisherImpl implements SmartmeterConfigPublisher {

    private final MqttGateway gateway;
    private final MqttBrokerProperties properties;
    private final ObjectMapper objectMapper;

    public SmartmeterConfigPublisherImpl(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper) {
        this.gateway = gateway;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(String smartmeterId, String macAddress) {
        String topic = properties.getOutboundTopic().getSmartmeterConfig().formatted(macAddress);
        SmartmeterConfigRequest request = new SmartmeterConfigRequest(smartmeterId);
        try {
            String payload = objectMapper.writeValueAsString(request);
            gateway.publish(topic, payload);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Erro ao serializar a configuração do SmartMeter",
                    e
            );
        }
    }

}
