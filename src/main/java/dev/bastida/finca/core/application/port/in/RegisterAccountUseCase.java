package dev.bastida.finca.core.application.port.in;

public interface RegisterAccountUseCase {
    RegisterAccountResponse registerAccount(RegisterAccountCommand command);
}

