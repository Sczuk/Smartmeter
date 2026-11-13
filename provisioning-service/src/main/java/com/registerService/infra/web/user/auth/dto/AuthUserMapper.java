package com.registerService.infra.web.user.auth.dto;

import com.registerService.domain.model.User;
import com.registerService.infra.web.user.auth.dto.request.AuthLoginUserRequest;
import com.registerService.infra.web.user.auth.dto.request.AuthRegisterUserRequest;
import org.mapstruct.Mapper;

@Mapper
public interface AuthUserMapper {

    public User toDomain(AuthRegisterUserRequest request);

    public User toDomain(AuthLoginUserRequest request);

}
