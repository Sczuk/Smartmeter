package energy_api.energy_iot_service.infra.persistence.device;

import energy_api.energy_iot_service.domain.model.Device;
import org.mapstruct.Mapper;

@Mapper
public interface DeviceMongoMapper {

    DeviceEntity toEntity(Device domain);

    Device toDomain(DeviceEntity entity);
}
