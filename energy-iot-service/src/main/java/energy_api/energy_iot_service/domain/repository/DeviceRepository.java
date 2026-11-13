package energy_api.energy_iot_service.domain.repository;

import energy_api.energy_iot_service.domain.model.Device;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository {

    Device save(Device device);

    void deleteById(String id);

    Device findBySmartmeter_IdAndId(String smartmeterId, String deviceId);

    List<Device> findBySmartmeter_Id(String smartmeterId);

    Device findById(String id);

    boolean existsById(String id);

    List<Device> findAll();

}
