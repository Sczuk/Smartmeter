package com.registerService.infra.web.device;

import com.registerService.application.useCases.device.CreateDevice;
import com.registerService.application.useCases.device.DeleteDeviceById;
import com.registerService.domain.model.Device;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
@Tag(name = "Device Controller", description = "device controller to control the devices of a user-controlled smartmeter")
public class DeviceController {

    private final CreateDevice createDevice;

    private final DeviceMapper mapper;

    private final DeleteDeviceById deleteDeviceById;

    public DeviceController(CreateDevice createDevice, DeviceMapper mapper, DeleteDeviceById deleteDeviceById) {
        this.createDevice = createDevice;
        this.mapper = mapper;
        this.deleteDeviceById = deleteDeviceById;
    }

    @PostMapping("/smartmeterId/{smartmeterId}/create")
    @Operation(summary = "Create Device", description = "creating a new device to connect to your smartmeter")
    public ResponseEntity<DeviceResponse> createDevice(@RequestBody @Valid DeviceRequest request,@PathVariable String smartmeterId, Authentication auth){
        Device device = mapper.toDomain(request);
        createDevice.execute(device, smartmeterId, auth.getName());
        return ResponseEntity.ok(mapper.toResponse(device));
    }

    @DeleteMapping("/delete/deviceId/{deviceId}/smartmeterId/{smartmeterId}")
    @Operation(summary = "Delete device specific", description = "Delete a device based on the Smartmeter's Id and the device Id.")
    public ResponseEntity<?> deleteDevice(@PathVariable String deviceId, @PathVariable String smartmeterId, Authentication auth){
        deleteDeviceById.execute(deviceId, smartmeterId, auth.getName());
        return ResponseEntity.noContent().build();
    }

}
