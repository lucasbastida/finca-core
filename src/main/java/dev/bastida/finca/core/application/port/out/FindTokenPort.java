package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Token;

import java.util.List;
import java.util.Optional;

public interface FindTokenPort {
    List<Token> findValidTokensByAccountId(Long id);

    Optional<Token> findByTokenValue(String value);
}
