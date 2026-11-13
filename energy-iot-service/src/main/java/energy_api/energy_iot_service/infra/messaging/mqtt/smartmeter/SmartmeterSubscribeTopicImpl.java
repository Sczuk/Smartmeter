package energy_api.energy_iot_service.infra.messaging.mqtt.smartmeter;

import energy_api.energy_iot_service.application.useCases.messaging.SmartmeterSubscribeTopic;
import energy_api.energy_iot_service.infra.messaging.mqtt.config.MqttBrokerProperties;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

public class SmartmeterSubscribeTopicImpl implements SmartmeterSubscribeTopic {

    private final MqttPahoMessageDrivenChannelAdapter inboundAdapter;
    private final MqttBrokerProperties properties;

    public SmartmeterSubscribeTopicImpl(MqttPahoMessageDrivenChannelAdapter inbound, MqttBrokerProperties properties) {
        this.inboundAdapter = inbound;
        this.properties = properties;
    }

    @Override
    public void subscribeToSmartmeterConfigTopic(String macAddress){
        String topic = properties.getInboundTopic().getSmartmeterConfig().formatted(macAddress);
        inboundAdapter.addTopic(topic, 1);
    }

    @Override
    public void subscribeToMeasurementsTopic(String smartmeterId) {
        String topic = properties.getInboundTopic().getMeasurements().formatted(smartmeterId);
        inboundAdapter.addTopic(topic, 1);
    }

    @Override
    public void unsubscribeFromSmartmeterConfigTopic(String macAddress){
        String topic = properties.getInboundTopic().getSmartmeterConfig().formatted(macAddress);
        inboundAdapter.removeTopic(topic);
    }

    @Override
    public void unsubscribeFromMeasurementsTopic(String smartmeterId) {
        String topic = properties.getInboundTopic().getMeasurements().formatted(smartmeterId);
        inboundAdapter.removeTopic(topic);
    }

}
