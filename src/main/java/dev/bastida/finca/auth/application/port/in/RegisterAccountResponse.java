package dev.bastida.finca.auth.application.port.in;

public record RegisterAccountResponse(String accessToken, String refreshToken) {
}
