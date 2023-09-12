package com.eshop.apigateway.jwt;

import com.eshop.apigateway.exceptions.ExceptionMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements GatewayFilter {

    private final JwtGatewayTokenProvider jwtGatewayTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = jwtGatewayTokenProvider.extractTokenFromRequest(exchange.getRequest());

        if (token != null) {
            try {
                if (jwtGatewayTokenProvider.isTokenValid(token)) {
                    log.info("Token is valid and has the required permissions.");
                    return chain.filter(exchange);
                }
            } catch (ExpiredJwtException ex) {
                log.warn("JWT has expired.");
                return handleJwtError(exchange, ex);
            } catch (MalformedJwtException | SignatureException ex) {
                log.warn("Invalid token.");
                return handleJwtError(exchange, ex);
            }
        }

        log.warn("Missing or invalid token.");
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> handleJwtError(ServerWebExchange exchange, JwtException ex) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ExceptionMessage response = new ExceptionMessage();
        response.setCode(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(ex.getMessage());
        response.setPath(exchange.getRequest().getPath().value());
        response.setMethod(exchange.getRequest().getMethod().name());

        try {
            byte[] responseBytes = objectMapper.writeValueAsBytes(response);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(responseBytes);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            return exchange.getResponse().setComplete();
        }
    }
}
