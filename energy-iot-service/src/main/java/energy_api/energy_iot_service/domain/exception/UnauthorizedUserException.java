package energy_api.energy_iot_service.domain.exception;

public class UnauthorizedUserException extends BaseException {

    private final String code = "UNAUTHORIZED_USER";
    private final int httpStatusCode = 403;

    public UnauthorizedUserException(String message) {
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