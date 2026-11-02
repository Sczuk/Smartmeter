package energy_api.energy_iot_service.infra.persistence.exception;

import energy_api.energy_iot_service.domain.exception.BaseException;

public class DeviceNotFoundException extends PersistenceBaseException {

    private final String code = "DEVICE_NOT_FOUND";

    public DeviceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return code;
    }
}
