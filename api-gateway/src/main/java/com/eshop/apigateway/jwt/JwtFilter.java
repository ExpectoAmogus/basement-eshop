package com.eshop.apigateway.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements GatewayFilter {

    private final JwtGatewayTokenProvider jwtGatewayTokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = jwtGatewayTokenProvider.extractTokenFromRequest(exchange.getRequest());

        //TODO: update filter
        if (token != null && jwtGatewayTokenProvider.isTokenValid(token)) {
            log.info("Token is valid and has the required permissions.");
            return chain.filter(exchange);
        } else {
            log.warn("Invalid or missing token.");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }
}
