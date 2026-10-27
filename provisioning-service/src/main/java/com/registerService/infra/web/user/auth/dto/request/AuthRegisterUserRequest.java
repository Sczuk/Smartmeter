package com.registerService.infra.web.user.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRegisterUserRequest {

    @NotBlank(message = "Name cannot be empty. ")
    private String name;

    @Email(message = "Needs a email to register.")
    private String email;

    @NotBlank(message = "Password cannot be empty. ")
    private String password;

}
