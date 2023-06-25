package dev.bastida.finca.auth.adapter.in.web;

import dev.bastida.finca.auth.application.port.in.RegisterAccountUseCase;
import dev.bastida.finca.auth.application.port.in.RegisterAccountUseCase.RegisterAccountCommand;
import dev.bastida.finca.auth.application.port.in.RegisterAccountUseCase.RegisterAccountResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
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
        final RegisterAccountCommand command = new RegisterAccountCommand(
                registerAccountRequest.getFirstName(),
                registerAccountRequest.getLastName(),
                registerAccountRequest.getPassword(),
                registerAccountRequest.getEmail()
        );
        return registerAccountUseCase.registerAccount(command);
    }

    @Data
    public static class RegisterAccountRequest {
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @NotBlank
        private String password;
        @NotBlank
        private String email;
    }
}
