package energy_api.energy_iot_service.domain.exception;

public class SmartmeterNotFoundException extends BaseException {

    private final String code = "SMARTMETER_NOT_FOUND";
    private final int httpStatusCode = 400;

    public SmartmeterNotFoundException(String message) {
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
