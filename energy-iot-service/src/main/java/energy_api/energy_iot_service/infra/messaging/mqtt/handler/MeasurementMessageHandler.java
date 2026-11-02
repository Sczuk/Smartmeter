package energy_api.energy_iot_service.infra.messaging.mqtt.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SaveMeasurements;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.MeasurementMqttMapper;
import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.request.MeasurementsMqttRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class MeasurementMessageHandler {

    private final MeasurementMqttMapper mapper;
    private final SaveMeasurements saveMeasurements;

    public MeasurementMessageHandler(MeasurementMqttMapper mapper, SaveMeasurements saveMeasurements) {
        this.mapper = mapper;
        this.saveMeasurements = saveMeasurements;
    }

    @ServiceActivator(inputChannel = "measurements")
    public void handler(MeasurementsMqttRequest request) throws JsonProcessingException {

        request.getDevices()
                .forEach(measurementsRequest -> {
                    DeviceMeasurement measurement = mapper.toDomain(measurementsRequest);
                    measurement.setMeasured(Instant.now());
                    measurement.setSmartmeterId(request.getSmartmeterId());
                    saveMeasurements.execute(measurement);
                });
    }
}
