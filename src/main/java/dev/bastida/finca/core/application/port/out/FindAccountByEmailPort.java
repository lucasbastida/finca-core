package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Account;

import java.util.Optional;

public interface FindAccountByEmailPort {
    Optional<Account> findByEmail(String email);
}
