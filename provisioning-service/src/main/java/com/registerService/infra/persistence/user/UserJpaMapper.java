package com.registerService.infra.persistence.user;

import com.registerService.domain.model.User;
import com.registerService.infra.config.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper
public interface UserJpaMapper {

    UserEntity toEntity(User domain, @Context CycleAvoidingMappingContext context);

    User toDomain(UserEntity entity, @Context CycleAvoidingMappingContext context);

}
