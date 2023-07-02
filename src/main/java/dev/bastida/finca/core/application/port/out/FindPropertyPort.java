package dev.bastida.finca.core.application.port.out;

import dev.bastida.finca.core.domain.Property;

import java.util.Optional;

public interface FindPropertyPort {
    Optional<Property> findById(Long propertyId, Long ownerId);
}
