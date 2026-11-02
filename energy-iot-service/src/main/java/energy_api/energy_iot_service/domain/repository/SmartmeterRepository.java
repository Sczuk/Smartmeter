package energy_api.energy_iot_service.domain.repository;

import energy_api.energy_iot_service.domain.model.Smartmeter;

import java.util.List;
import java.util.Optional;

public interface SmartmeterRepository {

    Smartmeter save(Smartmeter smartmeter);

    Smartmeter findByMacAddress(String macAddress);

    void deleteByMacAddress(String macAddress);

    Optional<Smartmeter> findById(String id);

    void deleteById(String id);

    boolean existsById(String id);

    List<Smartmeter> findAll();

    List<Smartmeter> findByActive(boolean active);

}
