package dev.bastida.finca.auth.adapter.in.web;

import dev.bastida.finca.auth.application.port.in.AuthenticateAccountUseCase;
import dev.bastida.finca.auth.application.port.in.AuthenticateAccountUseCase.AuthenticateAccountCommand;
import dev.bastida.finca.auth.application.port.in.AuthenticateAccountUseCase.AuthenticateAccountResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthenticationRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
