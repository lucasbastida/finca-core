package dev.bastida.finca.auth.application.port.in;

public interface RefreshTokenUseCase {
    RefreshTokenResponse refresh(RefreshTokenCommand command);
}
