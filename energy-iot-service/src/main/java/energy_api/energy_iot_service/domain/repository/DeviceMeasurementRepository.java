package energy_api.energy_iot_service.domain.repository;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;

import java.awt.print.Pageable;
import java.time.Instant;
import java.util.List;

public interface DeviceMeasurementRepository {

    DeviceMeasurement save(DeviceMeasurement deviceMeasurement);

    List<DeviceMeasurement> findAllByDeviceId(String deviceId, int page, int size);

    DeviceMeasurement findFirstByMetadata_DeviceIdOrderByMeasuredDesc(String deviceId);

    void deleteAllByDeviceId(String deviceId);

    DeviceMeasurement findFirstByMetadata_DeviceId(String deviceId);

    List<DeviceMeasurement> findByMeasuredGreaterThanEqualAndMeasuredLessThanAndMetadata_DeviceId(
            Instant start,
            Instant end,
            String deviceId
    );

    List<DeviceMeasurement> findByMetadata_DeviceId(String deviceId, int page, int size);

}
