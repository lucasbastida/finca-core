package dev.bastida.finca.auth.adapter.out.persistence;

import dev.bastida.finca.auth.domain.Account;
import dev.bastida.finca.auth.application.port.out.FindAccountByEmailPort;
import dev.bastida.finca.auth.application.port.out.RegisterAccountPort;
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
