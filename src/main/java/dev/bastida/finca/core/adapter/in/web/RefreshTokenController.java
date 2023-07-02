package dev.bastida.finca.core.adapter.in.web;

import dev.bastida.finca.core.application.port.in.RefreshTokenUseCase;
import dev.bastida.finca.core.application.port.in.RefreshTokenUseCase.RefreshTokenCommand;
import dev.bastida.finca.core.application.port.in.RefreshTokenUseCase.RefreshTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenUseCase refreshTokenUseCase;

    @PostMapping("/api/v1/auth/refresh")
    public RefreshTokenResponse refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        if (!authorization.startsWith("Bearer ")) {
            return null;
        }
        final String jwt = authorization.substring(7);
        final RefreshTokenCommand refreshTokenCommand = new RefreshTokenCommand(jwt);
        return refreshTokenUseCase.refresh(refreshTokenCommand);
    }
}
