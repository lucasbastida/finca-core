package dev.bastida.finca.core.application.port.in;

public interface AuthenticateAccountUseCase {
    AuthenticateAccountResponse authenticate(AuthenticateAccountCommand command);

    record AuthenticateAccountCommand(String email, String password) {
    }

    record AuthenticateAccountResponse(String accessToken, String refreshToken) {
    }
}
