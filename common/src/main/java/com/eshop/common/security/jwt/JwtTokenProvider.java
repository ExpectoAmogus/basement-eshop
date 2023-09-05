package com.eshop.common.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface JwtTokenProvider {
    boolean isTokenValid(String token);
    String extractUsername(String token);
    Collection<? extends GrantedAuthority> extractRoles(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
