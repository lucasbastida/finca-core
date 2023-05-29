package dev.bastida.finca.core.application.port.in;

public interface RefreshTokenUseCase {
    RefreshTokenResponse refresh(RefreshTokenCommand command);
}
