package energy_api.energy_iot_service.infra.persistence.smartmeter;

import energy_api.energy_iot_service.domain.model.Smartmeter;
import org.mapstruct.Mapper;

@Mapper
public interface SmartmeterMongoMapper{

    SmartmeterEntity toEntity(Smartmeter domain);

    Smartmeter toDomain(SmartmeterEntity entity);

}
