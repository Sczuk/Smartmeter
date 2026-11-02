package energy_api.energy_iot_service.infra.web.device;

import energy_api.energy_iot_service.application.useCases.device.CreateDevice;
import energy_api.energy_iot_service.application.useCases.device.DeleteDevice;
import energy_api.energy_iot_service.application.useCases.device.DeviceCommandControl;
import energy_api.energy_iot_service.domain.model.Device;
import energy_api.energy_iot_service.infra.web.device.dto.DeviceCreatedRequest;
import energy_api.energy_iot_service.infra.web.device.dto.DeviceCreatedResponse;
import energy_api.energy_iot_service.infra.web.device.dto.DeviceMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy-iot/devices")
public class DeviceController {

    private final CreateDevice createDevice;

    private final DeleteDevice deleteDevice;

    private final DeviceMapper mapper;

    private final DeviceCommandControl deviceCommandControl;

    public DeviceController(CreateDevice createDevice, DeleteDevice deleteDevice, DeviceMapper mapper, DeviceCommandControl deviceCommandControl) {
        this.createDevice = createDevice;
        this.deleteDevice = deleteDevice;
        this.mapper = mapper;
        this.deviceCommandControl = deviceCommandControl;
    }

    @PostMapping("/smartmeter/{smartmeterId}/deviceCreated")
    public ResponseEntity<DeviceCreatedResponse> createDevice(@RequestBody DeviceCreatedRequest request, @PathVariable String smartmeterId){
        Device device = mapper.toDomain(request);
        createDevice.execute(device, smartmeterId);
        return ResponseEntity.ok(mapper.toDeviceCreatedResponse(device));
    }

    @DeleteMapping("/deviceDeleted/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable String deviceId) {
        deleteDevice.execute(deviceId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/control/turnOn/{deviceId}")
    public ResponseEntity<?> turnOn(@PathVariable String deviceId, Authentication auth){
        deviceCommandControl.turnOn(deviceId, auth.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/control/turnOff/{deviceId}")
    public ResponseEntity<?> turnOff(@PathVariable String deviceId, Authentication auth){
        deviceCommandControl.turnOff(deviceId, auth.getName());
        return ResponseEntity.ok().build();
    }
}
