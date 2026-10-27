package com.registerService.infra.web.smartmeter.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmartmeterCreatedResponse {

    private String id;

    private String name;

    private String macAddress;

    private String userName;

}
