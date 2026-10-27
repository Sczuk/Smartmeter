package com.registerService.infra.web.smartmeter;

import com.registerService.application.useCases.smartmeter.CreateSmartmeter;
import com.registerService.application.useCases.smartmeter.DeleteSmartmeter;
import com.registerService.application.useCases.smartmeter.FindSmartmeter;
import com.registerService.application.useCases.smartmeter.ListSmartmetersByUserId;
import com.registerService.domain.model.Smartmeter;
import com.registerService.infra.web.device.DeviceMapper;
import com.registerService.infra.web.smartmeter.dto.SmartmeterMapper;
import com.registerService.infra.web.smartmeter.dto.request.SmartmeterRequest;
import com.registerService.infra.web.smartmeter.dto.response.ListSmartmeterWithUserNameResponse;
import com.registerService.infra.web.smartmeter.dto.response.ListSmartmetersResponse;
import com.registerService.infra.web.smartmeter.dto.response.SmartmeterCreatedResponse;
import com.registerService.infra.web.smartmeter.dto.response.SmartmeterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/smartmeter")
@Tag(name = "Smartmeter Controller", description = "Smartmeter controller to access smartmeter methods")
public class SmartmeterController {

    private final CreateSmartmeter createSmartmeter;

    private final FindSmartmeter findSmartmeter;

    private final SmartmeterMapper smartmeterMapper;

    private final DeviceMapper deviceMapper;

    private final ListSmartmetersByUserId listSmartmetersByUserId;

    private final DeleteSmartmeter deleteSmartmeter;


    public SmartmeterController(CreateSmartmeter createSmartmeter, FindSmartmeter findSmartmeter, SmartmeterMapper mapper, DeviceMapper deviceMapper, ListSmartmetersByUserId listSmartmetersByUserId, DeleteSmartmeter deleteSmartmeter) {
        this.createSmartmeter = createSmartmeter;
        this.findSmartmeter = findSmartmeter;
        this.smartmeterMapper = mapper;
        this.deviceMapper = deviceMapper;
        this.listSmartmetersByUserId = listSmartmetersByUserId;
        this.deleteSmartmeter = deleteSmartmeter;
    }

    @PostMapping("/create")
    @Operation(summary = "Create Smartmeter", description = "creating a new Smartmeter ")
    public ResponseEntity<SmartmeterCreatedResponse> createSmartmeter(@RequestBody @Valid SmartmeterRequest resquest, Authentication auth){
        return ResponseEntity.ok(smartmeterMapper.toSmartmeterCreatedResponse(createSmartmeter.execute(smartmeterMapper.toDomain(resquest), auth.getName())));
    }

    @GetMapping("/findSmartmeter/smartmeterId/{smartmeterId}")
    @Operation(summary = "find specific smartmeter", description = "You will find the specific Smartmeter you created, with the connected devices displayed alongside it.")
    public ResponseEntity<SmartmeterResponse> findSmartmeter(@PathVariable String smartmeterId, Authentication auth){
        Smartmeter smartmeter = findSmartmeter.execute(smartmeterId, auth.getName());
        SmartmeterResponse response = smartmeterMapper.toSmartmeterResponse(smartmeter);
        response.setDevices(smartmeter.getDevices().stream().map(deviceMapper::toResponse).toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/smartmeters")
    @Operation(summary = "Get all smartmeters", description = "With this method, you will find all the Smartmeters registered to your Id.")
    public ResponseEntity<ListSmartmeterWithUserNameResponse> listSmartmeter(Authentication auth){
        ListSmartmeterWithUserNameResponse response = new ListSmartmeterWithUserNameResponse();
        List<ListSmartmetersResponse> smartmeterResponse = listSmartmetersByUserId.execute(auth.getName())
                .stream()
                .map(smartmeter -> {
                    response.setName(smartmeter.getUser().getName());
                    return smartmeterMapper.toListSmartmeterResponse(smartmeter);})
                .toList();
        response.setSmartmeters(smartmeterResponse);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deleteSmartmeter/smartmeterId/{smartmeterId}")
    @Operation(summary = "Delete smartmeter", description = "You are going to delete a specific Smartmeter based on its SmartmeterId.")
    public ResponseEntity<?> deleteSmartmeter(@PathVariable String smartmeterId, Authentication auth){
        deleteSmartmeter.execute(smartmeterId,auth.getName());
        return ResponseEntity.noContent().build();
    }
}
