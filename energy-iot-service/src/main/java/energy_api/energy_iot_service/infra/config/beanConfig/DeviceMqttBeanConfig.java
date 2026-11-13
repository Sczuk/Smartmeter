package energy_api.energy_iot_service.infra.config.beanConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceCommandPublisher;
import energy_api.energy_iot_service.application.useCases.messaging.DeviceConfigPublisher;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttGateway;
import energy_api.energy_iot_service.infra.messaging.mqtt.device.DeviceCommandPublisherImpl;
import energy_api.energy_iot_service.infra.messaging.mqtt.device.DeviceConfigPublisherImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;

@Configuration
@IntegrationComponentScan(basePackages = "energy_api.energy_iot_service.infra.messaging.mqtt.config")
public class DeviceMqttBeanConfig {

    @Bean
    public DeviceCommandPublisher deviceCommandPublisher(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper){
        return new DeviceCommandPublisherImpl(gateway, properties, objectMapper);
    }

    @Bean
    public DeviceConfigPublisher deviceConfigPublisher(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper){
        return new DeviceConfigPublisherImpl(gateway, properties, objectMapper);
    }
}
