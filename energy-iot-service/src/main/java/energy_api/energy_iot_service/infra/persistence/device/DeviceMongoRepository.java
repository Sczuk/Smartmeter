package energy_api.energy_iot_service.infra.persistence.device;

import energy_api.energy_iot_service.domain.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceMongoRepository extends MongoRepository<DeviceEntity, String> {

    Optional<DeviceEntity> findBySmartmeter_IdAndId(String smartmeterId, String deviceId);

    Optional<List<Device>> findBySmartmeter_Id(String smartmeterId);

}
