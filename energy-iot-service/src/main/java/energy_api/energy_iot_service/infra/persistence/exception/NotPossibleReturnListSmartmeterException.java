package energy_api.energy_iot_service.infra.persistence.exception;

public class NotPossibleReturnListSmartmeterException extends PersistenceBaseException {

    private final String code = "NOT_POSSIBLE_RETURN_LIST_SMARTMETER_EXECPTION";

    public NotPossibleReturnListSmartmeterException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return code;
    }
}
