package dev.bastida.finca.auth.application.port.out;

import dev.bastida.finca.auth.domain.Account;

import java.util.Optional;

public interface FindAccountByEmailPort {
    Optional<Account> findByEmail(String email);
}
