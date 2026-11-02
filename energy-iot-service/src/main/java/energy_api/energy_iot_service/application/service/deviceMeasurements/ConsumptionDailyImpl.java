package energy_api.energy_iot_service.application.service.deviceMeasurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.ConsumptionDaily;
import energy_api.energy_iot_service.domain.exception.DateNotValidException;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementDailySummaryRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;
import energy_api.energy_iot_service.infra.persistence.exception.SmartmeterNotFoundException;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ConsumptionDailyImpl implements ConsumptionDaily {

    private final DeviceMeasurementDailySummaryRepository dailySummaryRepository;

    private final SmartmeterRepository smartmeterRepository;

    public ConsumptionDailyImpl(DeviceMeasurementDailySummaryRepository dailySummaryRepository, SmartmeterRepository smartmeterRepository) {
        this.dailySummaryRepository = dailySummaryRepository;
        this.smartmeterRepository = smartmeterRepository;
    }

    @Override
    public List<DeviceMeasurementDailySummary> execute(String deviceId, String smartmeterId, String userId, String from, String to) {
        smartmeterRepository.findById(smartmeterId).filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new SmartmeterNotFoundException("smartmeter not found"));

        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        LocalDate start = LocalDate.parse(from);
        LocalDate end = LocalDate.parse(to);
        if (end.isBefore(start) || ChronoUnit.DAYS.between(start, end) > 366) {
            throw new DateNotValidException("date is not valid");
        }
        Instant dateStart = start.atStartOfDay(zone).toInstant();
        Instant dateEnd = end.plusDays(1).atStartOfDay(zone).toInstant();
        List<DeviceMeasurementDailySummary> result = new ArrayList<>();

        if(deviceId == null||deviceId.isBlank()){
            List<DeviceMeasurementDailySummary> deviceMeasurementsWithSmartmeterId = dailySummaryRepository
                    .findByDateGreaterThanEqualAndDateLessThanAndSmartmeterId(dateStart, dateEnd, smartmeterId);

            Map<Instant, Double> energyByDate = new TreeMap<>();
            for (DeviceMeasurementDailySummary measurement : deviceMeasurementsWithSmartmeterId) {
                Instant date = measurement.getDate();
                energyByDate.merge(
                        date,
                        measurement.getTotalEnergyKWH(),
                        Double::sum
                );
            }

            for(Map.Entry<Instant, Double> entry : energyByDate.entrySet()){
                DeviceMeasurementDailySummary measurement = new DeviceMeasurementDailySummary(
                        null,
                        null,
                        entry.getValue(),
                        0,
                        0,
                        0,
                        entry.getKey()
                        );
                result.add(measurement);
            }
            return result;
        }

        return dailySummaryRepository
                .findByDateGreaterThanEqualAndDateLessThanAndDeviceId(dateStart,dateEnd, deviceId);
        }
    }

