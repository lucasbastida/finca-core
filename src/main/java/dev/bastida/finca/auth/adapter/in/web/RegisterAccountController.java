package dev.bastida.finca.auth.adapter.in.web;

import dev.bastida.finca.auth.application.port.in.RegisterAccountCommand;
import dev.bastida.finca.auth.application.port.in.RegisterAccountResponse;
import dev.bastida.finca.auth.application.port.in.RegisterAccountUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterAccountController {

    private final RegisterAccountUseCase registerAccountUseCase;

    @PostMapping("/api/v1/auth/register")
    public RegisterAccountResponse registerAccount(@Valid @RequestBody RegisterAccountRequest registerAccountRequest) {
        RegisterAccountCommand command = new RegisterAccountCommand(
                registerAccountRequest.getFirstName(),
                registerAccountRequest.getLastName(),
                registerAccountRequest.getPassword(),
                registerAccountRequest.getEmail()
        );
        return registerAccountUseCase.registerAccount(command);
    }
}
