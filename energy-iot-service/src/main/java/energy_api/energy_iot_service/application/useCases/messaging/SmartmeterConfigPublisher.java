package energy_api.energy_iot_service.application.useCases.messaging;

public interface SmartmeterConfigPublisher {

    void execute(String smartmeterId, String macAddress);

}
