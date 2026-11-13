package energy_api.energy_iot_service.infra.persistence.deviceMeasurementDailySumarry;

import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.util.List;

@Mapper
public interface DeviceMeasurementDailySummaryMongoMapper {

    DeviceMeasurementDailySummary toDomain(DeviceMeasurementDailySummaryEntity entity);

    DeviceMeasurementDailySummaryEntity toEntity(DeviceMeasurementDailySummary domain);



}
