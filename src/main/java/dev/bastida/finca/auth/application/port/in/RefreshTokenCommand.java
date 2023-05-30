package dev.bastida.finca.auth.application.port.in;

import org.springframework.util.StringUtils;

public record RefreshTokenCommand(String refreshToken) {
    public RefreshTokenCommand {
        if (!StringUtils.hasText(refreshToken)) {
            throw new IllegalArgumentException("refreshToken must not be empty");
        }
    }
}
