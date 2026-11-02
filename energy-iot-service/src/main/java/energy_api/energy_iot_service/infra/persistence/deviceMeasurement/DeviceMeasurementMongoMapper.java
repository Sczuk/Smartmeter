package energy_api.energy_iot_service.infra.persistence.deviceMeasurement;

import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DeviceMeasurementMongoMapper {

    @Mappings({
            @Mapping(source = "deviceId", target = "metadata.deviceId"),
            @Mapping(source = "smartmeterId", target = "metadata.smartmeterId")
    })
    DeviceMeasurementEntity toEntity(DeviceMeasurement domain);

    @Mappings({
            @Mapping(target = "deviceId", source = "metadata.deviceId"),
            @Mapping(target = "smartmeterId", source = "metadata.smartmeterId")
    })
    DeviceMeasurement toDomain(DeviceMeasurementEntity entity);

}
