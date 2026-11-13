package com.registerService.infra.security.roleConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionsSecurityConfig {

    USER_POST("user::post"),
    USER_DELETE("user::delete"),
    USER_PUT("user::put"),
    USER_GET("user::get"),

    ADMIN_POST("admin::post"),
    ADMIN_DELETE("admin::delete"),
    ADMIN_PUT("admin::put"),
    ADMIN_GET("admin::get");

    private final String permission;

}
