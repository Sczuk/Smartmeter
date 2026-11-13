package energy_api.energy_iot_service.infra.messaging.mqtt.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceConfigPublisher;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttGateway;
import energy_api.energy_iot_service.infra.messaging.mqtt.device.dto.DeviceConfigRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

public class DeviceConfigPublisherImpl implements DeviceConfigPublisher {

    private final MqttGateway gateway;
    private final MqttBrokerProperties properties;
    private final ObjectMapper objectMapper;

    public DeviceConfigPublisherImpl(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper) {
        this.gateway = gateway;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Device device) {
        String topic = properties.getOutboundTopic().getDeviceConfig().formatted(device.getSmartmeter().getId());
        DeviceConfigRequest request = new DeviceConfigRequest(device.getId(), device.getRelayPin(), device.getCurrentPin(), device.getVoltagePin());
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
