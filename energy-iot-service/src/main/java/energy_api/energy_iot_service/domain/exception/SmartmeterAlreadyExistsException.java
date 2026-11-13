package energy_api.energy_iot_service.domain.exception;

public class SmartmeterAlreadyExistsException extends BaseException {

    private final String code = "SMARTMETER_ALREADY_EXISTS";
    private final int httpStatusCode = 409;

    public SmartmeterAlreadyExistsException(String message) {
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