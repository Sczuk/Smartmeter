package energy_api.energy_iot_service.infra.persistence.deviceMeasurementDailySumarry;

import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.Instant;
import java.util.List;

public interface DeviceMeasurementDailySummaryMongoRepository extends MongoRepository<DeviceMeasurementDailySummaryEntity, ObjectId> {

    DeviceMeasurementDailySummaryEntity findByDateAndSmartmeterId(Instant date, String smartmeterId);

    @Query("{ 'date': { $gte: ?0, $lt: ?1 }, 'device_id': ?2 }")
    List<DeviceMeasurementDailySummaryEntity> findByDateGreaterThanEqualAndDateLessThanAndDeviceId(
            Instant start,
            Instant end,
            String deviceId
    );

    @Query("{ 'date': { $gte: ?0, $lt: ?1 }, 'smartmeter_id': ?2 }")
    List<DeviceMeasurementDailySummaryEntity> findByDateGreaterThanEqualAndDateLessThanAndSmartmeterId(
            Instant start,
            Instant end,
            String smartmeterId
    );



}
