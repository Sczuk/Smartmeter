package com.registerService.infra.security.keycloak;

import com.registerService.application.useCases.security.AuthTokens;
import com.registerService.application.useCases.security.IdentityProvider;
import com.registerService.domain.exception.user.InvalidUserValuesException;
import com.registerService.domain.exception.user.UserExistsException;
import com.registerService.domain.model.User;
import com.registerService.infra.security.exception.InvalidRefreshTokenException;
import com.registerService.infra.security.exception.LoginUnauthorizedException;
import com.registerService.infra.web.user.auth.dto.response.AuthUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class KeycloakIdentityProvider implements IdentityProvider {

    private static final Logger log = LoggerFactory.getLogger(KeycloakIdentityProvider.class);

    private static final ParameterizedTypeReference<Map<String, Object>> MAP_TYPE = new ParameterizedTypeReference<>() {};

    private final RestClient http = RestClient.create();

    private final String baseUrl;
    private final String realm;
    private final String adminClientId;
    private final String adminClientSecret;
    private final String loginClientId;
    private final String loginClientSecret;

    public KeycloakIdentityProvider(
            @Value("${keycloak.base-url}") String baseUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin.client-id}") String adminClientId,
            @Value("${keycloak.admin.client-secret}") String adminClientSecret,
            @Value("${keycloak.login.client-id}") String loginClientId,
            @Value("${keycloak.login.client-secret}") String loginClientSecret) {
        this.baseUrl = baseUrl;
        this.realm = realm;
        this.adminClientId = adminClientId;
        this.adminClientSecret = adminClientSecret;
        this.loginClientId = loginClientId;
        this.loginClientSecret = loginClientSecret;
    }

    @Override
    public UUID createUser(User user) {
        String adminToken = adminToken();
        String[] name = splitName(user.getName());

        Map<String, Object> body = Map.of(
                "username", user.getEmail(),
                "email", user.getEmail(),
                "firstName", name[0],
                "lastName", name[1],
                "enabled", true,
                "emailVerified", true,
                "credentials", List.of(Map.of(
                        "type", "password",
                        "value", user.getPassword(),
                        "temporary", false
                ))
        );

        UUID id;
        try {
            URI location = http.post()
                    .uri(adminUrl() + "/users")
                    .headers(h -> h.setBearerAuth(adminToken))
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .toBodilessEntity()
                    .getHeaders()
                    .getLocation();
            if (location == null) {
                throw new IllegalStateException("Keycloak did not return the new user's location");
            }
            String path = location.getPath();
            id = UUID.fromString(path.substring(path.lastIndexOf('/') + 1));
        } catch (HttpClientErrorException.Conflict e) {
            throw new UserExistsException("Email already registered");
        } catch (HttpClientErrorException.BadRequest e) {
            throw new InvalidUserValuesException("Keycloak rejected the user: " + e.getResponseBodyAsString());
        }

        try {
            assignRealmRole(adminToken, id, user.getRole().name());
        } catch (RuntimeException e) {
            deleteUser(id);
            throw e;
        }
        return id;
    }

    @Override
    public void deleteUser(UUID id) {
        try {
            http.delete()
                    .uri(adminUrl() + "/users/{id}", id)
                    .headers(h -> h.setBearerAuth(adminToken()))
                    .retrieve()
                    .toBodilessEntity();
        } catch (RuntimeException e) {
            log.error("Could not remove user {} from Keycloak; remove it manually", id, e);
        }
    }

    @Override
    public AuthTokens login(String email, String password) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", loginClientId);
        form.add("client_secret", loginClientSecret);
        form.add("username", email);
        form.add("password", password);

        try {
            Map<String, Object> res = postForm(form);
            return new AuthTokens(
                    (String) res.get("access_token"),
                    (String) res.get("refresh_token"),
                    asLong(res.get("expires_in")),
                    asLong(res.get("refresh_expires_in")),
                    (String) res.get("token_type")
            );
        } catch (HttpClientErrorException e) {
            throw new LoginUnauthorizedException("Invalid login");
        }
    }

    @Override
    public AuthTokens refresh(String refreshToken) {
        var form = new LinkedMultiValueMap<String, String>();
        form.add("grant_type", "refresh_token");
        form.add("client_id", loginClientId);
        form.add("client_secret", loginClientSecret);
        form.add("refresh_token", refreshToken);

        return http.post()
                .uri("/realms/smartmeter/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        (req, res) -> { throw new InvalidRefreshTokenException("invalid refresh token"); })
                .body(AuthTokens.class);
    }

    @Override
    public void logout(String refreshToken) {
        var form = new LinkedMultiValueMap<String, String>();
        form.add("client_id", loginClientId);
        form.add("client_secret", loginClientSecret);
        form.add("refresh_token", refreshToken);

        http.post()
                .uri("/protocol/openid-connect/logout")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .onStatus(status -> status.value() == 400, (req, res) -> { })
                .toBodilessEntity();
    }

    private void assignRealmRole(String adminToken, UUID userId, String roleName) {
        Map<String, Object> role = http.get()
                .uri(adminUrl() + "/roles/{name}", roleName)
                .headers(h -> h.setBearerAuth(adminToken))
                .retrieve()
                .body(MAP_TYPE);

        http.post()
                .uri(adminUrl() + "/users/{id}/role-mappings/realm", userId)
                .headers(h -> h.setBearerAuth(adminToken))
                .contentType(MediaType.APPLICATION_JSON)
                .body(List.of(role))
                .retrieve()
                .toBodilessEntity();
    }

    private String adminToken() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "client_credentials");
        form.add("client_id", adminClientId);
        form.add("client_secret", adminClientSecret);
        return (String) postForm(form).get("access_token");
    }

    private Map<String, Object> postForm(MultiValueMap<String, String> form) {
        return http.post()
                .uri(baseUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(form)
                .retrieve()
                .body(MAP_TYPE);
    }

    private String adminUrl() {
        return baseUrl + "/admin/realms/" + realm;
    }

    private static String[] splitName(String fullName) {
        String trimmed = fullName.trim();
        int space = trimmed.indexOf(' ');
        if (space < 0) {
            return new String[]{trimmed, trimmed};
        }
        return new String[]{trimmed.substring(0, space), trimmed.substring(space + 1).trim()};
    }

    private static long asLong(Object value) {
        return value instanceof Number n ? n.longValue() : 0L;
    }
}