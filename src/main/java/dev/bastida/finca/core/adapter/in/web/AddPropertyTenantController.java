package dev.bastida.finca.core.adapter.in.web;

import dev.bastida.finca.core.application.port.in.AddPropertyTenantUseCase;
import dev.bastida.finca.core.domain.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

import static dev.bastida.finca.core.application.port.in.AddPropertyTenantUseCase.AddTenantDto;
import static dev.bastida.finca.core.application.port.in.AddPropertyTenantUseCase.AddTenatResponse;

@RestController
@RequiredArgsConstructor
public class AddPropertyTenantController {

    private final AddPropertyTenantUseCase addPropertyTenantUseCase;

    @PostMapping("/api/v1/tenant")
    public NewTenantResponse addTenant(@Valid @RequestBody NewTenantRequest request, @AuthenticationPrincipal Account account) {
        final AddTenantDto dto = AddTenantDto.builder()
                .owner(account)
                .propertyId(request.propertyId)
                .expireAt(request.expireAt)
                .startAt(request.startAt)
                .endAt(request.endAt)
                .build();
        final AddTenatResponse addTenatResponse = addPropertyTenantUseCase.addTenant(dto);
        return new NewTenantResponse(addTenatResponse.shortlink());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NewTenantRequest {
        @NotNull
        private Long propertyId;
        @NotNull
        private OffsetDateTime expireAt;
        private OffsetDateTime startAt;
        private OffsetDateTime endAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewTenantResponse {
        private String shortlink;
    }
}
