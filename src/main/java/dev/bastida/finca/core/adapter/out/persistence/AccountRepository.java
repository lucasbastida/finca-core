package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
