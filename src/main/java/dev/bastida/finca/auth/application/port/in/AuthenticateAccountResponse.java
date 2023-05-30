package dev.bastida.finca.auth.application.port.in;

public record AuthenticateAccountResponse(String accessToken, String refreshToken) {
}
