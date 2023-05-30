package dev.bastida.finca.core.application.port.in;

public record AuthenticateAccountResponse(String accessToken, String refreshToken) {
}
