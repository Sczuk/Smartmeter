package energy_api.energy_iot_service.application.useCases.smartmeter;

public interface ActivateSmartmeter {

    boolean execute(String smartmeterId, boolean updateState);

}
