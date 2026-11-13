package energy_api.energy_iot_service.application.useCases.device;

public interface DeviceCommandControl {

    void turnOff( String deviceId, String email);

    void turnOn( String deviceId, String email);

}
