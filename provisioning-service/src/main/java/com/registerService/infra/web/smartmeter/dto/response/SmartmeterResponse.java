package com.registerService.infra.web.smartmeter.dto.response;

import com.registerService.infra.web.device.DeviceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SmartmeterResponse {

    private String id;

    private String userName;

    private String name;

    private String macAddress;

    private List<DeviceResponse> devices;

}
