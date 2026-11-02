package energy_api.energy_iot_service.infra.messaging.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttInboundConsumer {

    //@ServiceActivator(inputChannel = "energy-iot-service.mqttInputChannel")
    //public void consume(String payload) {
    //    log.info("Mensagem recebida via MQTT: {}", payload);
    //}
}