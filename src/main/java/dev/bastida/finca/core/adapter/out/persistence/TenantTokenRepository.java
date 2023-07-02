package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.domain.TenantToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantTokenRepository extends JpaRepository<TenantToken, Long> {
}
