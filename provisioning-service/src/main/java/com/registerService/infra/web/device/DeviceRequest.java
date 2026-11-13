package com.registerService.infra.web.device;

import com.registerService.domain.model.enums.DeviceType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceRequest {

    @NotBlank
    private String name;

    private DeviceType type;

}
