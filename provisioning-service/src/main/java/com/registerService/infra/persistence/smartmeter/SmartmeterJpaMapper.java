package com.registerService.infra.persistence.smartmeter;

import com.registerService.domain.model.Smartmeter;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper
public interface SmartmeterJpaMapper {

    SmartmeterEntity toEntity(Smartmeter domain, @Context CycleAvoidingMappingContext context);

    Smartmeter toDomain(SmartmeterEntity entity, @Context CycleAvoidingMappingContext context);

}
