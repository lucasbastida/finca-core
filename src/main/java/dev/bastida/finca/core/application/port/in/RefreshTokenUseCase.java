package dev.bastida.finca.core.application.port.in;

public interface RefreshTokenUseCase {
    RefreshTokenResponse refresh(RefreshTokenCommand command);

    record RefreshTokenCommand(String refreshToken) {
    }

    record RefreshTokenResponse(String accessToken, String refreshToken) {
    }
}
