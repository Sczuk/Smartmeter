package energy_api.energy_iot_service.infra.messaging.mqtt.config;

import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@MessagingGateway(defaultReplyChannel = "mqttOutboundChannel", defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void publish(@Header(MqttHeaders.TOPIC) String topic, String payload);

}
