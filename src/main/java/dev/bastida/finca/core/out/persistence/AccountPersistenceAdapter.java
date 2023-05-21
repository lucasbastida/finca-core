package dev.bastida.finca.core.out.persistence;

import dev.bastida.finca.core.application.domain.Account;
import dev.bastida.finca.core.application.port.out.RegisterAccountPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements RegisterAccountPort {

    private final AccountRepository userRepository;

    @Override
    public Account registerAccount(Account user) {
        return userRepository.save(user);
    }
}
