package energy_api.energy_iot_service.application.service.deviceMeasurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SaveMeasurementDailyScheduled;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.model.DeviceMeasurementDailySummary;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementDailySummaryRepository;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

public class SaveMeasurementDailyScheduledImpl implements SaveMeasurementDailyScheduled {

    private final DeviceRepository deviceRepository;

    private final DeviceMeasurementRepository deviceMeasurementRepository;

    private final DeviceMeasurementDailySummaryRepository deviceMeasurementDailySummaryRepository;

    public SaveMeasurementDailyScheduledImpl(DeviceRepository deviceRepository, DeviceMeasurementRepository deviceMeasurementRepository, DeviceMeasurementDailySummaryRepository deviceMeasurementDailySummaryRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceMeasurementRepository = deviceMeasurementRepository;
        this.deviceMeasurementDailySummaryRepository = deviceMeasurementDailySummaryRepository;
    }

    @Override
    public void execute() {
        ZoneId zone = ZoneId.of("America/Sao_Paulo");

        LocalDate yesterday = LocalDate.now(zone).minusDays(1);

        Instant start = yesterday
                .atStartOfDay(zone)
                .toInstant();

        Instant end = yesterday
                .plusDays(1)
                .atStartOfDay(zone)
                .toInstant();

        List<Device> devices = deviceRepository.findAll();
        for(Device device : devices){
            DeviceMeasurementDailySummary deviceMeasurementDailySummary = new DeviceMeasurementDailySummary();
            List<DeviceMeasurement> deviceMeasurements = deviceMeasurementRepository.findByMeasuredGreaterThanEqualAndMeasuredLessThanAndMetadata_DeviceId(start, end, device.getId());

            if (deviceMeasurements.isEmpty()) {
                continue;
            }

            deviceMeasurementDailySummary.setAverageCurrentA(
                    deviceMeasurements.stream()
                    .map(DeviceMeasurement::getCurrentA)
                    .filter(Objects::nonNull)
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0));

            deviceMeasurementDailySummary.setAveragePowerW(
                    deviceMeasurements.stream()
                    .map(DeviceMeasurement::getPowerW)
                    .filter(Objects::nonNull)
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0));

            deviceMeasurementDailySummary.setSampleCount(deviceMeasurements.size());
            deviceMeasurementDailySummary.setTotalEnergyKWH((deviceMeasurementDailySummary.getAveragePowerW() * 24) / 1000);
            deviceMeasurementDailySummary.setDate(start);
            deviceMeasurementDailySummary.setDeviceId(device.getId());
            deviceMeasurementDailySummary.setSmartmeterId(device.getSmartmeter().getId());
            deviceMeasurementDailySummaryRepository.save(deviceMeasurementDailySummary);
        }
    }

}
