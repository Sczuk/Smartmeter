package energy_api.energy_iot_service.infra.web.smartmeter;

import energy_api.energy_iot_service.application.useCases.smartmeter.CreateSmartmeter;
import energy_api.energy_iot_service.application.useCases.smartmeter.DeleteSmartmeter;
import energy_api.energy_iot_service.domain.model.Smartmeter;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.request.SmartmeterCreatedRequest;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.request.SmartmeterCreatedResponse;
import energy_api.energy_iot_service.infra.web.smartmeter.dto.SmartmeterMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy-iot/smartmeters")
public class SmartmeterController {

    private final DeleteSmartmeter deleteSmartmeter;

    private final CreateSmartmeter createSmartmeter;

    private final SmartmeterMapper mapper;

    public SmartmeterController(DeleteSmartmeter deleteSmartmeter, CreateSmartmeter createSmartmeter, SmartmeterMapper mapper) {
        this.deleteSmartmeter = deleteSmartmeter;
        this.createSmartmeter = createSmartmeter;
        this.mapper = mapper;
    }

    @PostMapping("/smartmeterCreated")
    public ResponseEntity<SmartmeterCreatedResponse> createSmartmeter(@RequestBody SmartmeterCreatedRequest request){
        Smartmeter smartmeter = mapper.toDomain(request);
        createSmartmeter.execute(smartmeter);
        return ResponseEntity.ok(mapper.toResponse(smartmeter));
    }

    @DeleteMapping("/smartmeterDeleted/{smartmeterId}")
    public ResponseEntity<?> smartmeterDeleted(@PathVariable String smartmeterId){
        deleteSmartmeter.execute(smartmeterId);
        return ResponseEntity.noContent().build();
    }
}
