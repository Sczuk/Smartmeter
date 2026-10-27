package com.registerService.infra.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_KEY = "bearer-key";

    private static final String KEYCLOAK_KEY = "keycloak-oauth";

    @Bean
    public OpenAPI customOpenAPI(
            @Value("${keycloak.base-url}") String keycloakBaseUrl,
            @Value("${keycloak.realm}") String realm){

        Info info = new Info()
                .title("Smarmeter")
                .version("1.0.0")
                .description("Smartmeter Java back-end");

        String openIdUrl = keycloakBaseUrl + "/realms/" + realm + "/protocol/openid-connect";

        SecurityScheme keycloakScheme = new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .flows(new OAuthFlows().authorizationCode(new OAuthFlow()
                        .authorizationUrl(openIdUrl + "/auth")
                        .tokenUrl(openIdUrl + "/token")
                        .scopes(new Scopes().addString("openid", "OpenID Connect"))));

        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization")
                .in(SecurityScheme.In.HEADER);

        Components components = new Components()
                .addSecuritySchemes(KEYCLOAK_KEY, keycloakScheme)
                .addSecuritySchemes(AUTHORIZATION_KEY, bearerScheme);

        return new OpenAPI().components(components)
                .addSecurityItem(new SecurityRequirement().addList(KEYCLOAK_KEY))
                .addSecurityItem(new SecurityRequirement().addList(AUTHORIZATION_KEY))
                .info(info);
    }

}