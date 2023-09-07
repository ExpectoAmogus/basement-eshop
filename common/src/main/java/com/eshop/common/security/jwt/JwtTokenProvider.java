package com.eshop.common.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.function.Function;

public interface JwtTokenProvider {
    boolean isTokenValid(String token);
    String extractUsername(String token);
    Collection<? extends GrantedAuthority> extractRoles(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
