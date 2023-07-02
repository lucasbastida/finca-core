package dev.bastida.finca.core.application.port.in;

import dev.bastida.finca.core.domain.Account;
import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

public interface AddPropertyTenantUseCase {

    AddTenatResponse addTenant(AddTenantDto dto);

    @Value
    @Builder
    class AddTenantDto {
        Account owner;
        Long propertyId;
        OffsetDateTime expireAt;
        OffsetDateTime startAt;
        OffsetDateTime endAt;
    }

    record AddTenatResponse(String shortlink) {
    }
}
