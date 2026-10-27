package com.registerService.infra.security.roleConfig;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum RoleSecurityConfig{

    USER(
            Set.of(
                    PermissionsSecurityConfig.USER_POST,
                    PermissionsSecurityConfig.USER_DELETE,
                    PermissionsSecurityConfig.USER_GET,
                    PermissionsSecurityConfig.USER_PUT
            )
    ),

    ADMIN(
            Set.of(
                    PermissionsSecurityConfig.ADMIN_POST,
                    PermissionsSecurityConfig.ADMIN_DELETE,
                    PermissionsSecurityConfig.ADMIN_GET,
                    PermissionsSecurityConfig.ADMIN_PUT
            )
    );

    private final Set<PermissionsSecurityConfig> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = new java.util.ArrayList<>(getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
