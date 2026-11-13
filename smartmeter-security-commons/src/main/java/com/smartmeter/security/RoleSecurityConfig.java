package com.smartmeter.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum RoleSecurityConfig {

    USER(Set.of(
            PermissionsSecurityConfig.USER_POST,
            PermissionsSecurityConfig.USER_DELETE,
            PermissionsSecurityConfig.USER_GET,
            PermissionsSecurityConfig.USER_PUT
    )),

    ADMIN(Set.of(
            PermissionsSecurityConfig.ADMIN_POST,
            PermissionsSecurityConfig.ADMIN_DELETE,
            PermissionsSecurityConfig.ADMIN_GET,
            PermissionsSecurityConfig.ADMIN_PUT
    )),

    SERVICE(Set.of());

    private final Set<PermissionsSecurityConfig> permissions;

    RoleSecurityConfig(Set<PermissionsSecurityConfig> permissions) {
        this.permissions = permissions;
    }

    public Set<PermissionsSecurityConfig> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}