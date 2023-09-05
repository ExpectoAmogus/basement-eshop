package com.eshop.apigateway.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
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
            log.info("Success!");
//            HttpHeaders headers = new HttpHeaders();
//            headers.addAll(exchange.getRequest().getHeaders());
//            exchange.getRequest().mutate().headers(httpHeaders -> httpHeaders.addAll(headers));
        } else {
            log.warn("Invalid or missing token");
        }

        return chain.filter(exchange);
    }
}
