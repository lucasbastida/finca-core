package dev.bastida.finca.core.application.service;

import dev.bastida.finca.SecurityConfigProperties;
import dev.bastida.finca.core.application.port.in.JwtUseCase;
import dev.bastida.finca.core.application.port.out.FindTokenPort;
import dev.bastida.finca.core.domain.Token;
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
public class JwtService implements JwtUseCase {

    private final SecurityConfigProperties securityConfigProperties;
    private final FindTokenPort findTokenPort;

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

    public boolean isInvalidJwt(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);

        final boolean tokenClaimNotExpired = isTokenNotExpired(jwt);
        final boolean isSameUsername = username.equals(userDetails.getUsername());
        final boolean isTokenValid = findTokenPort.findByTokenValue(jwt)
                .map(Token::isValid)
                .orElse(false);

        return !(isSameUsername && tokenClaimNotExpired && isTokenValid);
    }

    private boolean isTokenNotExpired(String jwt) {
        return !extractClaim(jwt, Claims::getExpiration).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), securityConfigProperties.getJwt().refreshTokenExpiration());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername(), securityConfigProperties.getJwt().refreshTokenExpiration());
    }

    private String generateToken(String username, long expiration) {
        final HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
