package dev.bastida.finca.core.application.port.in;

public record AuthenticateAccountCommand(String email, String password) {
}
