package dev.bastida.finca.auth.application.port.in;

public record AuthenticateAccountCommand(String email, String password) {
}
