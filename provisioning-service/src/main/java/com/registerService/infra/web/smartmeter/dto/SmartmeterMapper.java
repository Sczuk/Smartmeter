package com.registerService.infra.web.smartmeter.dto;

import com.registerService.domain.model.Smartmeter;
import com.registerService.infra.web.smartmeter.dto.request.SmartmeterRequest;

import com.registerService.infra.web.smartmeter.dto.response.SmartmeterCreatedResponse;
import com.registerService.infra.web.smartmeter.dto.response.SmartmeterResponse;
import com.registerService.infra.web.smartmeter.dto.response.ListSmartmetersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface SmartmeterMapper {

    public Smartmeter toDomain(SmartmeterRequest request);

    @Mapping(source = "user.name", target = "userName")
    public SmartmeterCreatedResponse toSmartmeterCreatedResponse(Smartmeter domain);

    @Mappings({
            @Mapping( source = "user.name", target = "userName"),
            @Mapping(target = "devices", ignore = true)}
    )
    public SmartmeterResponse toSmartmeterResponse(Smartmeter domain);

    @Mapping(target = "amountConnectedDevices", expression = "java(getAmountDevices(domain))")
    public ListSmartmetersResponse toListSmartmeterResponse(Smartmeter domain);

    default int getAmountDevices(Smartmeter smartmeter){
        if(smartmeter.getDevices() == null) return 0;
        return smartmeter.getDevices().size();
    }
}
