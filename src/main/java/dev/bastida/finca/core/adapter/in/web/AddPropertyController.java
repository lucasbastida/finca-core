package dev.bastida.finca.core.adapter.in.web;

import dev.bastida.finca.core.adapter.out.persistence.PropertyRepository;
import dev.bastida.finca.core.domain.Account;
import dev.bastida.finca.core.domain.Address;
import dev.bastida.finca.core.domain.Property;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class AddPropertyController {

    private final PropertyRepository propertyRepository;

    @PostMapping("/api/v1/property")
    public void addProperty(@RequestBody @Valid AddPropertyRequest request, @AuthenticationPrincipal Account account) {
        Address address = Address.builder()
                .country(request.country)
                .city(request.city)
                .stateProvince(request.stateProvince)
                .addressLine1(request.addressLine1)
                .addressLine2(request.addressLine2)
                .postalCode(request.postalCode)
                .latitude(request.latitude)
                .additionalInfo(request.additionalInfo)
                .build();
        Property property = Property.builder()
                .createdAt(Instant.now())
                .roomAmount(request.roomAmount)
                .bedroomAmount(request.bedroomAmount)
                .bathroomAmount(request.bathroomAmount)
                .garageAmount(request.garageAmount)
                .description(request.description)
                .address(address)
                .owner(account)
                .build();
        propertyRepository.save(property);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AddPropertyRequest {
        private short roomAmount;
        private Short bedroomAmount;
        private Short bathroomAmount;
        private Short garageAmount;
        private String description;
        private String country;
        private String city;
        private String stateProvince;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String postalCode;
        private String sortingCode;
        private Double latitude;
        private Double longitude;
        private String additionalInfo;
    }
}
