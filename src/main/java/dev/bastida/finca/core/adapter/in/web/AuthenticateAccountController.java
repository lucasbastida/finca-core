package dev.bastida.finca.core.adapter.in.web;

import dev.bastida.finca.core.application.port.in.AuthenticateAccountCommand;
import dev.bastida.finca.core.application.port.in.AuthenticateAccountResponse;
import dev.bastida.finca.core.application.port.in.AuthenticateAccountUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticateAccountController {

    private final AuthenticateAccountUseCase authenticateAccountUseCase;

    @PostMapping("/api/v1/auth/authenticate")
    public AuthenticateAccountResponse authenticate(@Valid @RequestBody AuthenticationRequest request) {
        final AuthenticateAccountCommand authenticateAccountCommand = new AuthenticateAccountCommand(
                request.getEmail(),
                request.getPassword()
        );
        return authenticateAccountUseCase.authenticate(authenticateAccountCommand);
    }
}
