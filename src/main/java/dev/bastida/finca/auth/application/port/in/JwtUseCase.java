package dev.bastida.finca.auth.application.port.in;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUseCase {
    boolean isInvalidJwt(String jwt, UserDetails userDetails);

    String extractUsername(String jwt);

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);
}
