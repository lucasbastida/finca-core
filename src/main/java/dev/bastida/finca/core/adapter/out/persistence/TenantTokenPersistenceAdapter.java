package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.application.port.out.SaveTenantPort;
import dev.bastida.finca.core.domain.Tenant;
import dev.bastida.finca.core.domain.TenantToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TenantTokenPersistenceAdapter implements SaveTenantPort {
    private final TenantTokenRepository tenantTokenRepository;

    @Override
    public Tenant saveTenant(TenantToken token) {
        final TenantToken tenantToken = tenantTokenRepository.save(token);
        return tenantToken.getTenant();
    }
}
