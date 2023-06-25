package dev.bastida.finca.auth.application.port.out;

import dev.bastida.finca.auth.domain.Token;

import java.util.List;

public interface SaveTokenPort {
    void save(Token token);

    void saveAll(List<Token> tokens);
}
