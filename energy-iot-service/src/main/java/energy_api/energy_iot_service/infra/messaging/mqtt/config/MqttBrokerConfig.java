package energy_api.energy_iot_service.infra.messaging.mqtt.config;

import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@EnableIntegration
@RequiredArgsConstructor
@Configuration
public class MqttBrokerConfig {

    private final MqttBrokerProperties mqttBrokerProperties;

    @Value("${mqtt.broker.user}")
    private String userMqtt;

    @Value("${mqtt.broker.password}")
    private String passwordMqtt;


    @Bean
    public MqttPahoClientFactory mqttClientFactory(){

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        options.setServerURIs(new String[]{mqttBrokerProperties.getHost()});
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setMaxInflight(100);
        options.setKeepAliveInterval(20);
        options.setUserName(userMqtt);
        options.setPassword(passwordMqtt.toCharArray());

        factory.setConnectionOptions(options);
        return factory;

    }

//--------subscriber---------
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        mqttBrokerProperties.getClientId() + "-inbound",
                        mqttClientFactory()
                );

        adapter.setCompletionTimeout(5000);
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

//---------publisher-----------
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler handler =
                new MqttPahoMessageHandler(mqttBrokerProperties.getClientId() + "-outbound", mqttClientFactory());
        handler.setAsync(true);
        handler.setDefaultQos(1);
        handler.setDefaultRetained(false);
        return handler;
    }


}
