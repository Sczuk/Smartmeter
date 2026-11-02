package energy_api.energy_iot_service.infra.messaging.mqtt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.mqtt")
@Data
@Component
public class MqttBrokerProperties {

    private String host;

    private String clientId;

    private InboundTopics inboundTopic = new InboundTopics();

    private OutboundTopics outboundTopic = new OutboundTopics();

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Data
    public static class InboundTopics {
        private String measurements;
        private String smartmeterConfig;
    }

    @Data
    public static class OutboundTopics {
        private String deviceCommand;
        private String deviceConfig;
        private String smartmeterConfig;
    }
}
