package dev.bastida.finca.core.application.service;

import dev.bastida.finca.core.application.domain.Account;
import dev.bastida.finca.core.application.port.in.RegisterAccountCommand;
import dev.bastida.finca.core.application.port.in.RegisterAccountUseCase;
import dev.bastida.finca.core.application.port.out.RegisterAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterAccountService implements RegisterAccountUseCase {

    private final PasswordEncoder passwordEncoder;
    private final RegisterAccountPort registerAccountPort;

    @Override
    public void registerAccount(RegisterAccountCommand command) {

        Account user = new Account(
                command.firstName(),
                command.lastName(),
                passwordEncoder.encode(command.password()),
                command.email());

        registerAccountPort.registerAccount(user);
    }
}
