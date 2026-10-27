package com.registerService.infra.security.keycloak;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Anexa o token de servico em cada chamada. Use somente no RestClient cujo baseUrl
 * e o energy-iot-service, para o token nunca ser enviado a outro destino.
 */
@Component
public class ServiceTokenInterceptor implements ClientHttpRequestInterceptor {

    private final ServiceTokenProvider tokenProvider;

    public ServiceTokenInterceptor(ServiceTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().setBearerAuth(tokenProvider.getToken());
        return execution.execute(request, body);
    }
}