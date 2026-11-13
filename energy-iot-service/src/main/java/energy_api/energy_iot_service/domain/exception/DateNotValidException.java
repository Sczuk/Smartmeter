package energy_api.energy_iot_service.domain.exception;

public class DateNotValidException extends BaseException{

    private final String code = "DATE_NOT_VALID";
    private final int httpStatusCode = 400;

    public DateNotValidException(String message) {
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

