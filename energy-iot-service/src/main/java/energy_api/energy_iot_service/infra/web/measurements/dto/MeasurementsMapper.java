package energy_api.energy_iot_service.infra.web.measurements.dto;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SumPower;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper
public interface MeasurementsMapper {

    DeviceMeasurementsResponse toDeviceMeasurementsResponse(DeviceMeasurement domain);

    @Mapping(target = "powerW", source = "power")
    SumPowerWResponse toSumPowerWResponse(SumPower.SumPowerRecord domain);

    @Mappings({@Mapping(target = "date", expression = "java(formatDate(domain))"),
               @Mapping(target = "kwh", source = "totalEnergyKWH")})
    ConsumptionDailyResponse toConsumptionDailyResponse(DeviceMeasurementDailySummary domain);

    default String formatDate(DeviceMeasurementDailySummary domain){
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.of("America/Sao_Paulo"));
        return formatter.format(domain.getDate());
    }

}
