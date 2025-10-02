package web.app.moldunity.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import web.app.moldunity.exception.JwtAuthenticationException;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token-expiration-minutes}")
    private Long tokenExpiration;

    @Value("${jwt.refresh-token-expiration-days}")
    private Long refreshTokenExpiration;

    private final String CLAIM_ROLE = "role";

    public String generateToken(UserDetails userDetails){
        final Date tokenExpiry = Date.from(Instant.now().plus(Duration.ofMinutes(tokenExpiration)));
        return generateToken(Map.of(), userDetails, tokenExpiry);
    }

    public String generateRefreshToken(UserDetails userDetails){
        final Date refreshTokenExpiry = Date.from(Instant.now().plus(Duration.ofDays(refreshTokenExpiration)));
        return generateToken(Map.of(), userDetails, refreshTokenExpiry);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails, Date expiration){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim(CLAIM_ROLE, userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get(CLAIM_ROLE)).toString();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtAuthenticationException("Token expired", e);
        } catch (JwtException e) {
            throw new JwtAuthenticationException("Invalid JWT", e);
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
}






























