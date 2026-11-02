package energy_api.energy_iot_service.infra.config.beanConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterConfigPublisher;
import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttGateway;
import energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter.SmartmeterConfigPublisherImpl;
import energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter.SmartmeterSubscribeTopicImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@Configuration
@IntegrationComponentScan(basePackages = "energy_api.energy_iot_service.infra.messaging.mqtt.config")
public class SmartmeterMqttBeanConfig {

    @Bean
    public SmartmeterConfigPublisher smartmeterConfigPublisher(MqttGateway gateway, MqttBrokerProperties properties, ObjectMapper objectMapper){
        return new SmartmeterConfigPublisherImpl(gateway, properties, objectMapper);
    }

    @Bean
    public SmartmeterSubscribeTopic smartmeterSubscribeTopic(MqttPahoMessageDrivenChannelAdapter inbound, MqttBrokerProperties properties){
        return new SmartmeterSubscribeTopicImpl(inbound, properties);
    }

}
