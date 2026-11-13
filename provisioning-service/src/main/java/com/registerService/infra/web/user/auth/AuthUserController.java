package com.registerService.infra.web.user.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.registerService.application.useCases.security.AuthTokens;
import com.registerService.application.useCases.security.IdentityProvider;
import com.registerService.application.useCases.user.auth.LoginUser;
import com.registerService.application.useCases.user.auth.RegisterUser;
import com.registerService.domain.model.User;
import com.registerService.infra.web.user.auth.dto.AuthUserMapper;
import com.registerService.infra.web.user.auth.dto.request.AuthLoginUserRequest;
import com.registerService.infra.web.user.auth.dto.request.AuthRegisterUserRequest;
import com.registerService.infra.web.user.auth.dto.response.AuthUserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Authentication", description = "User authentication for token retrieval")
public class AuthUserController {

    private final AuthUserMapper mapper;

    private final RegisterUser registerUser;

    private final LoginUser loginUser;

    private final IdentityProvider identifyProvider;

    public AuthUserController(AuthUserMapper mapper, RegisterUser registerUser, LoginUser loginUser, IdentityProvider identifyProvider) {
        this.mapper = mapper;
        this.registerUser = registerUser;
        this.loginUser = loginUser;
        this.identifyProvider = identifyProvider;
    }

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Creates the user in Keycloak and its local profile, returning the tokens")
    public ResponseEntity<AuthUserResponse> registerUser(@RequestBody @Valid AuthRegisterUserRequest request){
        User user = mapper.toDomain(request);
        registerUser.execute(user);

        return ResponseEntity.ok(AuthUserResponse.from(loginUser.execute(user)));
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Login for an existing user, returning the Keycloak tokens")
    public ResponseEntity<AuthUserResponse> loginUser(@RequestBody @Valid AuthLoginUserRequest request){
        return ResponseEntity.ok(AuthUserResponse.from(loginUser.execute(mapper.toDomain(request))));
    }

    @PostMapping("/refresh")
    public AuthTokens refresh(@Valid @RequestBody RefreshRequest request) {
        return identifyProvider.refresh(request.refreshToken());
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@Valid @RequestBody RefreshRequest request) {
        identifyProvider.logout(request.refreshToken());
    }

    public record RefreshRequest(@NotBlank @JsonProperty("refresh_token") String refreshToken) {}
}