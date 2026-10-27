package com.registerService.infra.security.keycloak;

import com.registerService.infra.security.roleConfig.RoleSecurityConfig;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Set<GrantedAuthority> authorities = extractRealmRoles(jwt).stream()
                .flatMap(role -> toAuthorities(role).stream())
                .collect(Collectors.<GrantedAuthority>toSet());

        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }

    private Set<String> extractRealmRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        if (realmAccess == null || !(realmAccess.get("roles") instanceof Collection<?> roles)) {
            return Set.of();
        }
        return roles.stream().map(Object::toString).collect(Collectors.toSet());
    }

    private List<SimpleGrantedAuthority> toAuthorities(String role) {
        return Arrays.stream(RoleSecurityConfig.values())
                .filter(r -> r.name().equals(role))
                .findFirst()
                .map(RoleSecurityConfig::getAuthorities)
                .orElse(List.of());
    }
}