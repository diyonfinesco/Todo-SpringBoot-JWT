package com.diyonfinesco.todo.config;

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

    private static final String SECRET_KEY = "dADa8Dw5vQrHZvMJz035vVDuDym8sjT0KsFQhZJJmMEFCqbwzOx1Iorr9ZGhSu0pqGfQeqGOg16Bj3qhHTa0zQZbED83lhOUVVOK0jg5fm5Y7aarZxfQIiEdveBBjeKkO8LPcZhE2kV2W0DbyADqzOF8D8MzW3dyPs661jMsjiosaYC1ZYBm8Sc3TfR0yzZuPYtJP2oDHAw2krrvAYhAr5HwhTkiU02pBtxmlhVvs633hXXMx2eLwS50BMvU218S6lYvViwcGYpjzql830spMbCBIShBhb3XHvQZMllpwCercCOlMX1qQtADGJlO9lyD3faeuEu4JYzFi1xQxlQ2qiw2cD6Ok0GDlaKKPvD1iQYT1x4swCZW1U0rm7ghBJXY2k4jgmsV9vBXGmKt77yKAMWlC1MOyMkFh23gfTouKRjeT3rQZk59Ysvbktr3Vw9gQRlKIDm4bEAewGteqWKILMsjRe2mViUU6FRaameUfKSACyg3OYeUQmKsFYdo8xwo";

    private static final int JWT_EXPIRATION = 86_400_000;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        return (extractUsername(token).equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
