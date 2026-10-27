package com.registerService.infra.integration.device.dto;

import com.registerService.domain.model.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class DeviceCreatedRequest {

    private String id;

    private DeviceType type;

    private String name;

    private int relayPin;

    private int currentPin;

    private int voltagePin;

}
