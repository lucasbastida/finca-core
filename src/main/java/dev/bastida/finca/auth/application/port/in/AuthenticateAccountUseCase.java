package dev.bastida.finca.auth.application.port.in;

public interface AuthenticateAccountUseCase {
    AuthenticateAccountResponse authenticate(AuthenticateAccountCommand command);
}
