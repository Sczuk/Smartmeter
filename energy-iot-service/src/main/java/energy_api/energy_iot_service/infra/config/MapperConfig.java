package energy_api.energy_iot_service.infra.config;

import energy_api.energy_iot_service.infra.messaging.mqtt.handler.dto.MeasurementMqttMapper;
import energy_api.energy_iot_service.infra.persistence.device.DeviceMongoMapper;
import energy_api.energy_iot_service.infra.persistence.deviceMeasurement.DeviceMeasurementMongoMapper;
import energy_api.energy_iot_service.infra.persistence.deviceMeasurementDailySumarry.DeviceMeasurementDailySummaryMongoMapper;
import energy_api.energy_iot_service.infra.persistence.smartmeter.SmartmeterMongoMapper;
import energy_api.energy_iot_service.infra.web.device.dto.DeviceMapper;
import energy_api.energy_iot_service.infra.web.measurements.dto.MeasurementsMapper;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.SmartmeterMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public SmartmeterMongoMapper smartmeterMongoMapper(){
        return Mappers.getMapper(SmartmeterMongoMapper.class);
    }

    @Bean
    public DeviceMeasurementMongoMapper deviceMeasurementMongoMapper(){
        return Mappers.getMapper(DeviceMeasurementMongoMapper.class);
    }

    @Bean
    public DeviceMongoMapper deviceMongoMapper(){
        return Mappers.getMapper(DeviceMongoMapper.class);
    }

    @Bean
    public DeviceMapper deviceMapper(){
        return Mappers.getMapper(DeviceMapper.class);
    }

    @Bean
    public SmartmeterMapper smartmeterMapper(){
        return Mappers.getMapper(SmartmeterMapper.class);
    }

    @Bean
    public MeasurementMqttMapper measurementMqttMapper(){return Mappers.getMapper(MeasurementMqttMapper.class);}

    @Bean
    public MeasurementsMapper measurementsMapper(){return Mappers.getMapper(MeasurementsMapper.class);}

    @Bean
    public DeviceMeasurementDailySummaryMongoMapper deviceMeasurementDailySummaryMongoMapper(){
        return Mappers.getMapper(DeviceMeasurementDailySummaryMongoMapper.class);
    }
}
