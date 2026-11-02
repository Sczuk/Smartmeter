package energy_api.energy_iot_service.application.useCases.smartmeter;

import energy_api.energy_iot_service.domain.model.Smartmeter;

public interface CreateSmartmeter {

    Smartmeter execute(Smartmeter smartmeter);

}
