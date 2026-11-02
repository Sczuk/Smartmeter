package energy_api.energy_iot_service.domain.exception;


public class DeviceAlreadyExistsException extends BaseException {

    private final String code = "DEVICE_ALREADY_EXISTS";
    private final int httpStatusCode = 409;

    public DeviceAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

}
