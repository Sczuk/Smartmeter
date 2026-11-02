package energy_api.energy_iot_service.application.useCases.messaging;

public interface SmartmeterSubscribeTopic {

    void subscribeToSmartmeterConfigTopic(String macAddress);

    void subscribeToMeasurementsTopic(String smartmeterId);

    void unsubscribeFromSmartmeterConfigTopic(String macAddress);

    void unsubscribeFromMeasurementsTopic(String smartmeterId);

}
