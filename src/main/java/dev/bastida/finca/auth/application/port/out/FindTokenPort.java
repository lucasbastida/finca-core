package dev.bastida.finca.auth.application.port.out;

import dev.bastida.finca.auth.domain.Token;

import java.util.List;
import java.util.Optional;

public interface FindTokenPort {
    List<Token> findValidTokensByAccountId(Long id);

    Optional<Token> findByTokenValue(String value);
}
