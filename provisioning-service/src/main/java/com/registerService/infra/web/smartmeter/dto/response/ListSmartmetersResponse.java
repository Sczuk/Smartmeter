package com.registerService.infra.web.smartmeter.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListSmartmetersResponse {

    private String name;

    private String id;

    private String macAddress;

    private int amountConnectedDevices;

}
