package energy_api.energy_iot_service.infra.web.smartmeter.dto;

import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.request.SmartmeterCreatedRequest;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.request.SmartmeterCreatedResponse;
import org.mapstruct.Mapper;

@Mapper
public interface SmartmeterMapper {

    Smartmeter toDomain(SmartmeterCreatedRequest smartmeterCreatedRequest);

    SmartmeterCreatedResponse toResponse(Smartmeter smartmeter);
}
