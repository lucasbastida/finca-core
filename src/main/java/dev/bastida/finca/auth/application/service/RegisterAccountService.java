package dev.bastida.finca.auth.application.service;

import dev.bastida.finca.auth.application.port.in.RegisterAccountUseCase;
import dev.bastida.finca.auth.application.port.out.RegisterAccountPort;
import dev.bastida.finca.auth.application.port.out.SaveTokenPort;
import dev.bastida.finca.auth.domain.Account;
import dev.bastida.finca.auth.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAccountService implements RegisterAccountUseCase {

    private final PasswordEncoder passwordEncoder;
    private final RegisterAccountPort registerAccountPort;
    private final JwtService jwtService;
    private final SaveTokenPort saveTokenPort;

    @Override
    public RegisterAccountResponse registerAccount(RegisterAccountCommand command) {

        Account user = new Account(
                command.firstName(),
                command.lastName(),
                passwordEncoder.encode(command.password()),
                command.email());

        registerAccountPort.registerAccount(user);

        final String token = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, token);

        return new RegisterAccountResponse(token, refreshToken);
    }

    private void saveUserToken(Account account, String jwt) {
        final Token token = Token.builder()
                .account(account)
                .value(jwt)
                .expired(false)
                .revoked(false)
                .build();
        saveTokenPort.save(token);
    }
}
