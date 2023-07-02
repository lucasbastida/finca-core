package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Tenant;
import dev.bastida.finca.core.domain.TenantToken;

public interface SaveTenantPort {
    Tenant saveTenant(TenantToken token);
}
