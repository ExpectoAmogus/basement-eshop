package com.eshop.inventoryservice.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtInventoryTokenProvider {
    @Value("${secret.key.jwt}")
    private String SECRET_KEY;

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Collection<? extends GrantedAuthority> extractRoles(String token) {
        final Claims claims = extarctAllClaims(token);

        if (claims.get("role") instanceof Collection<?> roles) {
            return roles.stream()
                    .filter(role -> role instanceof Map) // Ensure it's a Map
                    .map(role -> (Map<?, ?>) role) // Cast to Map
                    .filter(roleMap -> roleMap.containsKey("authority")) // Ensure it has "authority" key
                    .map(roleMap -> roleMap.get("authority").toString()) // Extract "authority" value as String
                    .map(SimpleGrantedAuthority::new)
                    .toList();
        }
        return Collections.emptyList();

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extarctAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extarctAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
