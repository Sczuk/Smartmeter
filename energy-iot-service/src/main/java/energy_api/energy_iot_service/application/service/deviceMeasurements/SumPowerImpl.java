package energy_api.energy_iot_service.application.service.deviceMeasurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.SumPower;
import energy_api.energy_iot_service.domain.exception.SmartmeterNotFoundException;
import energy_api.energy_iot_service.domain.exception.UnauthorizedUserException;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SumPowerImpl implements SumPower {

    private final DeviceMeasurementRepository measurementRepository;

    private final DeviceRepository deviceRepository;

    private final SmartmeterRepository smartmeterRepository;

    public SumPowerImpl(DeviceMeasurementRepository measurementRepository, DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
        this.smartmeterRepository = smartmeterRepository;
    }

    @Override
    public List<SumPowerRecord> execute(String smartmeterId, String userId, int hours) {
        smartmeterRepository.findById(smartmeterId)
                .filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new SmartmeterNotFoundException("smartmeter not found"));

        List<Device> devices = deviceRepository.findBySmartmeter_Id(smartmeterId);
        int minutes = hours * 60;

        Map<Instant, Double> totalByMinute = new TreeMap<>();
        for (Device device : devices) {
            if(!device.getSmartmeter().getUserId().equals(userId)){throw new UnauthorizedUserException("user not authorized");}

            for (DeviceMeasurement measurement : measurementRepository.findByMetadata_DeviceId(device.getId(), 0, minutes)) {
                Instant minute = measurement.getMeasured().truncatedTo(ChronoUnit.MINUTES);
                totalByMinute.merge(minute, measurement.getPowerW(), Double::sum);
            }
        }

        return totalByMinute.entrySet().stream()
                .map(e -> new SumPowerRecord(
                        e.getKey(),
                        e.getValue()))
                .toList();
    }


}
