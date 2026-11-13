package energy_api.energy_iot_service.infra.config.beanConfig;

import energy_api.energy_iot_service.application.service.deviceMeasurements.*;
import energy_api.energy_iot_service.application.useCases.deviceMeasurement.*;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementDailySummaryRepository;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceMeasurementsBeanConfig {

    @Bean
    public SaveMeasurements saveMeasurements(DeviceMeasurementRepository repository){
        return new SaveMeasurementsImpl(repository);
    }

    @Bean
    public SaveMeasurementDailyScheduled saveMeasurementDailyScheduled(DeviceRepository deviceRepository, DeviceMeasurementRepository deviceMeasurementRepository, DeviceMeasurementDailySummaryRepository deviceMeasurementDailySummaryRepository){
        return new SaveMeasurementDailyScheduledImpl(deviceRepository, deviceMeasurementRepository, deviceMeasurementDailySummaryRepository);
    }

    @Bean
    public ListLatestMeasurements listLatestMeasurements(DeviceMeasurementRepository measurementRepository, DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository){
        return new ListLatestMeasurementsImpl(measurementRepository, deviceRepository, smartmeterRepository);
    }

    @Bean
    public ConsumptionDaily consumptionDaily(DeviceMeasurementDailySummaryRepository dailySummaryRepository, SmartmeterRepository smartmeterRepository){
        return new ConsumptionDailyImpl(dailySummaryRepository, smartmeterRepository);
    }

    @Bean
    public SumPower sumPower(DeviceMeasurementRepository measurementRepository, DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository){
        return new SumPowerImpl(measurementRepository, deviceRepository, smartmeterRepository);
    }

}
