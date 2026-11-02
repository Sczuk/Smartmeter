package energy_api.energy_iot_service.infra.persistence.device;

import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.infra.persistence.exception.DeviceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository {

    private final DeviceMongoRepository repository;

    private final DeviceMongoMapper mapper;

    public DeviceRepositoryImpl(DeviceMongoRepository repository, DeviceMongoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Device save(Device device) {
        return mapper.toDomain(repository.save(mapper.toEntity(device)));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public Device findBySmartmeter_IdAndId(String smartmeterId, String deviceId) {
        return mapper.toDomain(repository.findBySmartmeter_IdAndId(smartmeterId, deviceId)
                .orElseThrow(()-> new DeviceNotFoundException("device by "+smartmeterId+" smartmeter id not found")));
    }

    @Override
    public List<Device> findBySmartmeter_Id(String smartmeterId) {
        return repository.findBySmartmeter_Id(smartmeterId)
                .orElseThrow(()-> new DeviceNotFoundException("Devices by "+smartmeterId+" not found"));
    }

    @Override
    public Device findById(String id) {
        return mapper.toDomain(repository.findById(id).orElseThrow(()-> new DeviceNotFoundException("device by "+id+" not found") ));
    }

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public List<Device> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }
}
