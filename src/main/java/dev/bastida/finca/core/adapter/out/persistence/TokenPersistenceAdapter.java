package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.application.port.out.FindTokenPort;
import dev.bastida.finca.core.application.port.out.SaveTokenPort;
import dev.bastida.finca.core.domain.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenPersistenceAdapter implements FindTokenPort, SaveTokenPort {

    private final TokenRepository tokenRepository;

    @Override
    public List<Token> findValidTokensByAccountId(Long id) {
        return tokenRepository.findAllValidTokensByUser(id);
    }

    @Override
    public Optional<Token> findByTokenValue(String value) {
        return tokenRepository.findByValue(value);
    }

    @Override
    public void save(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public void saveAll(List<Token> tokens) {
        tokenRepository.saveAll(tokens);
    }
}
