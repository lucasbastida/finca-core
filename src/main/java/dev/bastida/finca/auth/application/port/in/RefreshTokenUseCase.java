package dev.bastida.finca.auth.application.port.in;

import org.springframework.util.StringUtils;

public interface RefreshTokenUseCase {
    RefreshTokenResponse refresh(RefreshTokenCommand command);

    record RefreshTokenCommand(String refreshToken) {
        public RefreshTokenCommand {
            if (!StringUtils.hasText(refreshToken)) {
                throw new IllegalArgumentException("refreshToken must not be empty");
            }
        }
    }

    record RefreshTokenResponse(String accessToken, String refreshToken) {
    }
}
