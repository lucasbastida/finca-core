package dev.bastida.finca.core.application.service;

import dev.bastida.finca.core.adapter.in.filter.JwtService;
import dev.bastida.finca.core.application.port.in.RefreshTokenCommand;
import dev.bastida.finca.core.application.port.in.RefreshTokenResponse;
import dev.bastida.finca.core.application.port.in.RefreshTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements RefreshTokenUseCase {

    private final JwtService jwtService;
    private final EmailUserDetailService userDetailService;

    @Override
    public RefreshTokenResponse refresh(RefreshTokenCommand command) {
        final String username = jwtService.extractUsername(command.refreshToken());
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);
        if (!jwtService.isTokenValid(command.refreshToken(), userDetails)) {
            return null;
        }

        final String token = jwtService.generateToken(userDetails);

        return new RefreshTokenResponse(
                token,
                command.refreshToken()
        );
    }
}
