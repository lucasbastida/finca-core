package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.application.port.out.FindAccountByEmailPort;
import dev.bastida.finca.core.application.port.out.RegisterAccountPort;
import dev.bastida.finca.core.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements RegisterAccountPort, FindAccountByEmailPort {

    private final AccountRepository userRepository;

    @Override
    public Account registerAccount(Account user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
