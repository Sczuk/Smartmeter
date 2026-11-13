package com.registerService.infra.integration.smartmeter.dto;

import com.registerService.domain.model.Smartmeter;
import org.mapstruct.Mapper;import org.mapstruct.Mapping;import org.mapstruct.Mappings;

@Mapper
public interface SmartmeterIntegrationMapper {


    @Mapping(source = "user.id", target = "userId")
    SmartmeterCreatedResquest toSmartmeterCreatedResquest(Smartmeter smartmeter);

    SmartmeterDeletedResquest toSmartmeterDeletedRequest(Smartmeter smartmeter);

}
