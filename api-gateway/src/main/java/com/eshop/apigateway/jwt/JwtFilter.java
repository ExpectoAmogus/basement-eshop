package com.eshop.apigateway.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtFilter implements GatewayFilter {

    private final JwtGatewayTokenProvider jwtGatewayTokenProvider;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = jwtGatewayTokenProvider.extractTokenFromRequest(exchange.getRequest());

        if (token != null && jwtGatewayTokenProvider.isTokenValid(token)) {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }

        return chain.filter(exchange);
    }
}
