package energy_api.energy_iot_service.application.service.smartmeter;

import energy_api.energy_iot_service.application.useCases.smartmeter.DeleteSmartmeter;
import energy_api.energy_iot_service.domain.exception.SmartmeterAlreadyExistsException;
import energy_api.energy_iot_service.domain.exception.SmartmeterNotFoundException;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.domain.repository.DeviceRepository;
import energy_api.energy_iot_service.domain.repository.SmartmeterRepository;

import java.util.List;

public class DeleteSmartmeterImpl implements DeleteSmartmeter {

    private final SmartmeterRepository smartmeterRepository;

    private final DeviceRepository deviceRepository;

    public DeleteSmartmeterImpl(SmartmeterRepository smartmeterRepository, DeviceRepository deviceRepository) {
        this.smartmeterRepository = smartmeterRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void execute(String smartmeterId) {
        if(smartmeterRepository.findById(smartmeterId).isEmpty()){
            throw new SmartmeterNotFoundException("smartmeter by "+smartmeterId+" id not found");
        }

        List<Device> devices = deviceRepository.findBySmartmeter_Id(smartmeterId);

        if(!devices.isEmpty()){
            for(Device listDevices : devices){
                deviceRepository.deleteById(listDevices.getId());
            }
        }
        
        smartmeterRepository.deleteById(smartmeterId);
    }


}
