package energy_api.energy_iot_service.application.service.deviceMeasurements;

import energy_api.energy_iot_service.application.useCases.deviceMeasurement.ListLatestMeasurements;
import energy_api.energy_iot_service.domain.enums.DeviceState;
import energy_api.energy_iot_service.domain.exception.SmartmeterNotFoundException;
import energy_api.energy_iot_service.domain.exception.UnauthorizedUserException;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.model.DeviceMeasurement;
import energy_api.energy_iot_service.domain.repository.DeviceMeasurementRepository;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;

import java.util.ArrayList;
import java.util.List;

public class ListLatestMeasurementsImpl implements ListLatestMeasurements {

    private final DeviceMeasurementRepository measurementRepository;

    private final DeviceRepository deviceRepository;

    private final SmartmeterRepository smartmeterRepository;

    public ListLatestMeasurementsImpl(DeviceMeasurementRepository measurementRepository, DeviceRepository deviceRepository, SmartmeterRepository smartmeterRepository) {
        this.measurementRepository = measurementRepository;
        this.deviceRepository = deviceRepository;
        this.smartmeterRepository = smartmeterRepository;
    }

    @Override
    public List<DeviceMeasurement> execute(String smartmeterId, String userId) {
       smartmeterRepository.findById(smartmeterId)
                .filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new SmartmeterNotFoundException("smartmeter not found"));

        List<Device> devices = deviceRepository.findBySmartmeter_Id(smartmeterId);
        List<DeviceMeasurement> measurements = new ArrayList<>();

        for(Device device : devices){

            if(!device.getSmartmeter().getUserId().equals(userId)){
                throw new UnauthorizedUserException("user not authorized");
            }

            DeviceMeasurement measurement = measurementRepository.findFirstByMetadata_DeviceIdOrderByMeasuredDesc(device.getId());
            if(measurement == null){
                continue;
            }
            if(measurement.getState().equals(DeviceState.OFFLINE)){
                measurement.setPowerW(0);
                measurement.setCurrentA(0);
                measurement.setVoltageV(0);
            }
            measurements.add(measurement);
        }

        return measurements;
    }

}
