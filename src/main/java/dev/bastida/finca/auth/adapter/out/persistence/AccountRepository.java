package dev.bastida.finca.auth.adapter.out.persistence;

import dev.bastida.finca.auth.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
