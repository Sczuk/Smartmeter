package energy_api.energy_iot_service.infra.messaging.mqtt.handler.transformers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request.SmartmeterConfigRequest;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

@Component
public class SmartmeterConfigTransformer {

    private final ObjectMapper objectMapper;

    public SmartmeterConfigTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Transformer(inputChannel = "smartmeterConfigRaw", outputChannel = "smartmeterConfig")
    public SmartmeterConfigRequest transformPayload(String rawPayload){
        try{
            return objectMapper.readValue(rawPayload, SmartmeterConfigRequest.class);
        }catch (JsonProcessingException e){
            throw new IllegalStateException(
                    "Erro ao serializar a configuração do SmartMeter",
                    e
            );
        }
    }

}
