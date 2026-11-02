package energy_api.energy_iot_service.infra.messaging.mqtt.handler;

import org.springframework.integration.annotation.Router;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class HandlerMqtt {

    private static final Pattern MEASUREMENTS = Pattern.compile("smartmeter/[^/]+/measurements");
    private static final Pattern CONFIG = Pattern.compile("smartmeter/config/smartmeter/[^/]+");

    @Router(inputChannel = "mqttInputChannel")
    public String route(Message<?> message) {
        String topic = message.getHeaders()
                .get(MqttHeaders.RECEIVED_TOPIC, String.class);

        if (topic.matches(MEASUREMENTS.pattern())) {
            return "measurementsRaw";
        }

        if (topic.matches(CONFIG.pattern())) {
            return "smartmeterConfigRaw";
        }

        return "nullChannel";
    }

}
