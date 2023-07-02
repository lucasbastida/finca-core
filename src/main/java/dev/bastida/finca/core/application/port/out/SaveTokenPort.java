package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Token;

import java.util.List;

public interface SaveTokenPort {
    void save(Token token);

    void saveAll(List<Token> tokens);
}
