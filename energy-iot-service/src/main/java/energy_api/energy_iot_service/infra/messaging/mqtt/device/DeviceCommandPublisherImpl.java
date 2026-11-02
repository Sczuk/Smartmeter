package energy_api.energy_iot_service.infra.messaging.mqtt.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceCommandPublisher;
import energy_api.energy_iot_service.domain.enums.DeviceCommand;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttGateway;
import energy_api.energy_iot_service.infra.messaging.mqtt.device.dto.DeviceCommandRequest;
import org.springframework.stereotype.Component;

public class DeviceCommandPublisherImpl implements DeviceCommandPublisher {

    private final MqttGateway gateway;
    private final MqttBrokerProperties properties;
    private final ObjectMapper objectMapper;

    public DeviceCommandPublisherImpl(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper) {
        this.gateway = gateway;
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publishCommand(Device device, DeviceCommand command) {
        String topic = properties.getOutboundTopic().getDeviceCommand().formatted(device.getSmartmeter().getId());
        DeviceCommandRequest request = new DeviceCommandRequest(device.getId(), command);
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
