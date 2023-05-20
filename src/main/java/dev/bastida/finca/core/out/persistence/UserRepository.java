package dev.bastida.finca.core.out.persistence;

import dev.bastida.finca.core.application.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
}
