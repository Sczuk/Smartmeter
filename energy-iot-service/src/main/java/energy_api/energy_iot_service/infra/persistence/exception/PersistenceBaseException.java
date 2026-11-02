package energy_api.energy_iot_service.infra.persistence.exception;

public abstract class PersistenceBaseException extends RuntimeException {

    public PersistenceBaseException(String message) {
        super(message);
    }

    public abstract String getCode();

}
