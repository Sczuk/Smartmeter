package energy_api.energy_iot_service.domain.exception;

public class UnauthorizedCommandException extends BaseException {

    private final String code = "UNAUTHORIZED_COMMAND";
    private final int httpStatusCode = 403;

    public UnauthorizedCommandException(String message) {
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
