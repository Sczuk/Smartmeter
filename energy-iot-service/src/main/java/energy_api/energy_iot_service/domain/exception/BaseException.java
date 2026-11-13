package energy_api.energy_iot_service.domain.exception;

public abstract class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public abstract String getCode();

    public abstract int getHttpStatusCode();

}

