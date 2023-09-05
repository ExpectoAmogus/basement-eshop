package com.eshop.userservice.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private  static final String SECRET_KEY = "0BfnKiNubNkdDFu9JjgaS6HxPVEoXzQ4SCwx2VnX71u23L9hUPfL6V9QB/83jOgunc/88vzXv3UJZ7N3V7wSJqFjC1pJXcFD2h2ptmKhS6kIXrCgKMz4YxZJ2UWaLH2wifXd/nFTxyeZ31GDYXiHCd35+0sxTWNY7wgC2SuMcdG3AcOtDJpIF2w4FRr+VGEq+2nEPMksILrxyDogLEJbql0lyuA4R3mDWcEqeknW18Btxc9L8xV6+fuWB5Eq6sp69Y59niAO88cp+DV5ZoKpVt/YUfQafoLrZhLwpfyVY4bucCQmVM2F4MKWAX2VVl2EZjLh4hviU+SmXaz2fCAd0+cx25+iQ661qVIVGJCQQiI=";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities());
        return generationToken(claims, userDetails);
    }

    public String generationToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
