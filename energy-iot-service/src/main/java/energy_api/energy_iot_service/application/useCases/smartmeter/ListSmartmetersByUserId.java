package energy_api.energy_iot_service.application.useCases.smartmeter;

import energy_api.energy_iot_service.domain.model.Smartmeter;

import java.util.List;

public interface ListSmartmetersByUserId {

    List<Smartmeter> execute();
}
