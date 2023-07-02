package dev.bastida.finca.core.application.port.in;

public interface RegisterAccountUseCase {
    RegisterAccountResponse registerAccount(RegisterAccountCommand command);

    record RegisterAccountCommand(String firstName, String lastName, String password, String email) {
    }

    record RegisterAccountResponse(String accessToken, String refreshToken) {
    }
}

