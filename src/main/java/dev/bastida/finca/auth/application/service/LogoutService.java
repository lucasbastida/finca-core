package dev.bastida.finca.auth.application.service;

import dev.bastida.finca.auth.adapter.out.persistence.TokenRepository;
import dev.bastida.finca.auth.domain.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authorization.substring(7);

        final Token token = tokenRepository.findByToken(jwt)
                .orElse(null);

        if (token == null) {
            return;
        }

        token.setExpired(true);
        token.setRevoked(true);
        tokenRepository.save(token);
        SecurityContextHolder.clearContext();
    }
}
