package energy_api.energy_iot_service.infra.persistence.exception;

import energy_api.energy_iot_service.domain.exception.BaseException;

public class SmartmeterNotFoundException extends PersistenceBaseException {

    private final String code = "SMARTMETER_NOT_FOUND";

    public SmartmeterNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return code;
    }
}
