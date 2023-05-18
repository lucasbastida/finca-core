package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.out.persistence.User;

public interface RegisterUserPort {
    void registerUser(User user);
}
