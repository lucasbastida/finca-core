package dev.bastida.finca.core.application.service;

import dev.bastida.finca.core.application.port.in.AddPropertyTenantUseCase;
import dev.bastida.finca.core.application.port.out.FindPropertyPort;
import dev.bastida.finca.core.application.port.out.SaveTenantPort;
import dev.bastida.finca.core.domain.Property;
import dev.bastida.finca.core.domain.Tenant;
import dev.bastida.finca.core.domain.TenantToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AddTenantService implements AddPropertyTenantUseCase {
    private final FindPropertyPort findPropertyPort;
    private final SaveTenantPort saveTenantPort;

    @Override
    public AddTenatResponse addTenant(AddTenantDto dto) {
        if (dto.getExpireAt().isAfter(OffsetDateTime.now())) {
            throw new IllegalArgumentException();
        }
        final Property property = findPropertyPort.findById(
                        dto.getOwner().getId(),
                        dto.getPropertyId())
                .orElseThrow();
        final Tenant tenant = Tenant.builder()
                .property(property)
                .endAt(dto.getEndAt())
                .startAt(dto.getStartAt())
                .build();
        final TenantToken tenantToken = TenantToken.builder()
                .expireAt(dto.getExpireAt())
                .tenant(tenant)
                .build();
        saveTenantPort.saveTenant(tenantToken);
        return new AddTenatResponse(tenantToken.getShortlink().toString());
    }
}
