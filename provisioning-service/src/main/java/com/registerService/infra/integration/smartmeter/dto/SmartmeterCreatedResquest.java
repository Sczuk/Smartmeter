package com.registerService.infra.integration.smartmeter.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SmartmeterCreatedResquest {

    private String id;

    private String userId;

    private String macAddress;

    private String name;

}
