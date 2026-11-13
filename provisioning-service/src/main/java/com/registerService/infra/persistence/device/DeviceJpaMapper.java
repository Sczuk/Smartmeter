package com.registerService.infra.persistence.device;

import com.registerService.domain.model.Device;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper
public interface DeviceJpaMapper {

    DeviceEntity toEntity(Device domain, @Context CycleAvoidingMappingContext context);

    Device toDomain(DeviceEntity entity, @Context CycleAvoidingMappingContext context);

}
