package com.registerService.infra.web.device;


import com.registerService.domain.model.enums.DeviceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceResponse {

    private String id;

    private String deviceName;

    private DeviceType type;

    private String smartmeterName;

    private int[] channels;

}
