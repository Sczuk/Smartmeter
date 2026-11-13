package energy_api.energy_iot_service.infra.persistence.smartmeter;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SmartmeterMongoRepository extends MongoRepository<SmartmeterEntity, String> {

    Optional<SmartmeterEntity> findByMacAddress(String macAddress);

    void deleteByMacAddress(String macAddress);

    Optional<List<SmartmeterEntity>> findByActive(boolean active);

}
