package energy_api.energy_iot_service.infra.persistence.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.infra.persistence.exception.DeviceMeasurementNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public class DeviceMeasurementRepositoryImpl implements DeviceMeasurementRepository {

    private final DeviceMeasurementMongoRepository repository;

    private final DeviceMeasurementMongoMapper mapper;

    public DeviceMeasurementRepositoryImpl(DeviceMeasurementMongoRepository repository, DeviceMeasurementMongoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DeviceMeasurement save(DeviceMeasurement deviceMeasurement) {
        return mapper.toDomain(repository.save(mapper.toEntity(deviceMeasurement)));
    }

    @Override
    public List<DeviceMeasurement> findAllByDeviceId(String deviceId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<DeviceMeasurementEntity> entityPage = repository.findAllByMetadata_DeviceId(deviceId, pageable);

        return entityPage.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public DeviceMeasurement findFirstByMetadata_DeviceIdOrderByMeasuredDesc(String deviceId) {
        return mapper.toDomain(repository.findFirstByMetadata_DeviceIdOrderByMeasuredDesc(deviceId)
                .orElseThrow( () -> new DeviceMeasurementNotFoundException("device measuerement by "+deviceId+" device id not found")));
    }

    @Override
    public void deleteAllByDeviceId(String deviceId) {
        repository.deleteAllByMetadata_DeviceId(deviceId);
    }

    @Override
    public DeviceMeasurement findFirstByMetadata_DeviceId(String deviceId) {
        return mapper.toDomain(repository.findFirstByMetadata_DeviceId(deviceId).orElseThrow(() -> new DeviceMeasurementNotFoundException("not possible return this measurement")));
    }

    @Override
    public List<DeviceMeasurement> findByMeasuredGreaterThanEqualAndMeasuredLessThanAndMetadata_DeviceId(Instant start, Instant end, String deviceId) {
        return repository.findByMeasuredGreaterThanEqualAndMeasuredLessThanAndMetadata_DeviceId(start, end, deviceId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<DeviceMeasurement> findByMetadata_DeviceId(String deviceId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<DeviceMeasurementEntity> entityPage = repository.findAllByMetadata_DeviceId(deviceId, pageable);
        return entityPage.getContent()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
