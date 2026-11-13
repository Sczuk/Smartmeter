package energy_api.energy_iot_service.infra.messaging.mqtt.handler.transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request.MeasurementsMqttRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class MeasurementTransformer {

    private final ObjectMapper objectMapper;
    public MeasurementTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Transformer(inputChannel = "measurementsRaw", outputChannel = "measurements")
    public MeasurementsMqttRequest transformPayload(String rawPayload) {
        try {
            return objectMapper.readValue(
                    rawPayload,
                    MeasurementsMqttRequest.class
            );
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(
                    "Erro ao desserializar as medições do SmartMeter",
                    e
            );
        }
    }

}
