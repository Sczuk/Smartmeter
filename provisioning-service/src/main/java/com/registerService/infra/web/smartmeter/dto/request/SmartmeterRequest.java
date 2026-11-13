package com.registerService.infra.web.smartmeter.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartmeterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String macAddress;

}
