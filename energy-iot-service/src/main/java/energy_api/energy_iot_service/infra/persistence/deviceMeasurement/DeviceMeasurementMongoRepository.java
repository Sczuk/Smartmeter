package energy_api.energy_iot_service.infra.persistence.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceMeasurementMongoRepository extends MongoRepository<DeviceMeasurementEntity, String> {

    void deleteAllByMetadata_DeviceId(String deviceId);

    Page<DeviceMeasurementEntity> findAllByMetadata_DeviceId(String deviceId, Pageable pageable);

    @Query("{ 'measured': { $gte: ?0, $lt: ?1 }, 'deviceId': ?2 }")
    List<DeviceMeasurementEntity> findByMeasuredGreaterThanEqualAndMeasuredLessThanAndMetadata_DeviceId(Instant start, Instant end, String deviceId);

    Optional<DeviceMeasurementEntity> findFirstByMetadata_DeviceIdOrderByMeasuredDesc(String deviceId);

    Optional<DeviceMeasurementEntity> findFirstByMetadata_DeviceId(String deviceId);

    List<DeviceMeasurementEntity> findByMetadata_DeviceId(String deviceId, java.awt.print.Pageable pageable);
}
