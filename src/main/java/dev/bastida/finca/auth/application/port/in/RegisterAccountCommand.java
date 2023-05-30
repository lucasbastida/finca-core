package dev.bastida.finca.auth.application.port.in;

import org.springframework.util.StringUtils;

public record RegisterAccountCommand(String firstName, String lastName, String password, String email) {
    public RegisterAccountCommand {
        if (!StringUtils.hasText(firstName)) {
            throw new IllegalArgumentException("firstName must not be empty");
        }
        if (!StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("lastName must not be empty");
        }
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("password must not be empty");
        }
        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("email must not be empty");
        }
    }
}
