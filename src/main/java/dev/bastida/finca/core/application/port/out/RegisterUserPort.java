package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.application.domain.User;

public interface RegisterUserPort {
    void registerUser(User user);
}
