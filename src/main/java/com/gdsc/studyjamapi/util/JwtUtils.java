package com.gdsc.studyjamapi.util;

import com.gdsc.studyjamapi.common.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
  public static String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public static String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
        .signWith(getSigningKey())
        .compact();
  }

  private static SecretKey getSigningKey() {
    String jwtSigningSecret =
        System.getenv().getOrDefault("JWT_SIGNING_KEY", Constants.DEFAULT_SECRET_KEY);
    byte[] keyBytes = Decoders.BASE64.decode(jwtSigningSecret);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public static boolean isTokenValid(String token, UserDetails userDetails) {
    String username = extractSubject(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private static boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public static String extractSubject(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public static Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private static <R> R extractClaim(String token, Function<Claims, R> claimsResolver) {
    Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private static Claims extractAllClaims(String token) {
    Jws<Claims> jws = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
    return jws.getPayload();
  }
}
