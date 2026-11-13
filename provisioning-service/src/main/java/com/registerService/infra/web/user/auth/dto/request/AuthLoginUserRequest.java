package com.registerService.infra.web.user.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthLoginUserRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

}
