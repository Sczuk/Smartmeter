package energy_api.energy_iot_service.domain.repository;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;

import java.time.Instant;
import java.util.List;

public interface DeviceMeasurementDailySummaryRepository {

    DeviceMeasurementDailySummary save(DeviceMeasurementDailySummary deviceMeasurementsDailySummary);

    DeviceMeasurementDailySummary findByDateAndSmartmeterId(Instant date, String smartmeterId);

    List<DeviceMeasurementDailySummary> findByDateGreaterThanEqualAndDateLessThanAndDeviceId(
            Instant start,
            Instant end,
            String deviceId
    );

    List<DeviceMeasurementDailySummary> findByDateGreaterThanEqualAndDateLessThanAndSmartmeterId(
            Instant start,
            Instant end,
            String smartmeterId
    );


    //DeviceMeasurementDailySummary findAllByDeviceId(String deviceId);



}
