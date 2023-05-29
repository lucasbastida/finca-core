package dev.bastida.finca.core.application.port.in;

public interface AuthenticateAccountUseCase {
    AuthenticateAccountResponse authenticate(AuthenticateAccountCommand command);
}
