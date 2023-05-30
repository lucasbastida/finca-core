package dev.bastida.finca.auth.application.port.in;

public interface RegisterAccountUseCase {
    RegisterAccountResponse registerAccount(RegisterAccountCommand command);
}

