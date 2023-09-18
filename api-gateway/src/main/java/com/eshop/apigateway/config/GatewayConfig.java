package com.eshop.apigateway.config;

import com.eshop.apigateway.jwt.JwtFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, JwtFilter jwtFilter){
        return builder.routes()
                .route("product-query-service", r -> r.path("/api/product/**", "/api/excel/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://product-query-service"))

                .route("product-command-service", r -> r.path("/api/product/**", "/api/excel/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://product-command-service"))

                .route("article-service", r -> r.path("/api/article/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://article-service"))

                .route("cart-service", r -> r.path("/api/cart/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://cart-service"))

                .route("image-service", r -> r.path("/api/image/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://image-service"))

                .route("inventory-service", r -> r.path("/api/inventory/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://inventory-service"))

                .route("order-service", r -> r.path("/api/order/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://order-service"))

                .route("ticket-service", r -> r.path("/api/ticket/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://ticket-service"))

                .route("user-service", r -> r.path("/api/user/**", "/api/admin/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("lb://user-service"))

                .route("user-service", r -> r.path("/api/auth/**")
                        .uri("lb://user-service"))

                .route("discovery-server", r -> r.path("/eureka/web")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))

                .route("discovery-server-static", r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))

                .build();
    }
}
