package energy_api.energy_iot_service.infra.persistence.deviceMeasurementDailySumarry;

import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementDailySummaryRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public class DeviceMeasurementDailySummaryRepositoryImpl implements DeviceMeasurementDailySummaryRepository {

    private final DeviceMeasurementDailySummaryMongoRepository repository;

    private final DeviceMeasurementDailySummaryMongoMapper mapper;

    public DeviceMeasurementDailySummaryRepositoryImpl(DeviceMeasurementDailySummaryMongoRepository repository, DeviceMeasurementDailySummaryMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DeviceMeasurementDailySummary save(DeviceMeasurementDailySummary deviceMeasurementsDailySummary) {
        return mapper.toDomain(repository.save(mapper.toEntity(deviceMeasurementsDailySummary)));
    }

    @Override
    public DeviceMeasurementDailySummary findByDateAndSmartmeterId(Instant date, String smartmeterId) {
        return mapper.toDomain(repository.findByDateAndSmartmeterId(date, smartmeterId));
    }

    @Override
    public List<DeviceMeasurementDailySummary> findByDateGreaterThanEqualAndDateLessThanAndDeviceId(Instant start, Instant end, String deviceId) {
        return repository.findByDateGreaterThanEqualAndDateLessThanAndDeviceId(start, end, deviceId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<DeviceMeasurementDailySummary> findByDateGreaterThanEqualAndDateLessThanAndSmartmeterId(Instant start, Instant end, String smartmeterId) {
        return repository.findByDateGreaterThanEqualAndDateLessThanAndSmartmeterId(start, end, smartmeterId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
