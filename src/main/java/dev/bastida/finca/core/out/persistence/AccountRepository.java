package dev.bastida.finca.core.out.persistence;

import dev.bastida.finca.core.application.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountRepository extends JpaRepository<Account, Long> {
}
