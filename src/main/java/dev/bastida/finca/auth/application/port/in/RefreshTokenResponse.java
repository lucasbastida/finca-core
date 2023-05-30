package dev.bastida.finca.auth.application.port.in;

public record RefreshTokenResponse(String accessToken, String refreshToken) {
}
