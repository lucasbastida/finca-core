package dev.bastida.finca.core.in.web;

import dev.bastida.finca.core.application.port.out.RegisterUserPort;
import dev.bastida.finca.core.application.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserPort registerUserPort;

    @PostMapping("/register")
    public void registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User(
                registerUserRequest.getFirstName(),
                registerUserRequest.getLastName(),
                registerUserRequest.getPassword(),
                registerUserRequest.getEmail());
        registerUserPort.registerUser(user);
    }
}
