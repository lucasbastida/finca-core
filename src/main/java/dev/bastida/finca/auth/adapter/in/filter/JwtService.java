package dev.bastida.finca.auth.adapter.in.filter;

import dev.bastida.finca.auth.adapter.out.persistence.TokenRepository;
import dev.bastida.finca.config.SecurityConfigProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final SecurityConfigProperties securityConfigProperties;
    private final TokenRepository tokenRepository;

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsFunction) {
        final Claims claims = extractAllClaims(jwt);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSignInKey() {
        final byte[] decode = Decoders.BASE64.decode(securityConfigProperties.getJwt().secret());
        return Keys.hmacShaKeyFor(decode);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        final boolean tokenClaimNotExpired = isTokenNotExpired(jwt);
        final boolean isSameUsername = username.equals(userDetails.getUsername());

        final Boolean isTokenValid = tokenRepository.findByToken(jwt)
                .map(token -> token.isExpired() && token.isRevoked())
                .orElse(false);

        return isSameUsername && tokenClaimNotExpired && isTokenValid;
    }

    private boolean isTokenNotExpired(String jwt) {
        return !extractClaim(jwt, Claims::getExpiration).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        final HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + securityConfigProperties.getJwt().expiration()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        final HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + securityConfigProperties.getJwt().refreshTokenExpiration()))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
