package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.application.port.out.FindPropertyPort;
import dev.bastida.finca.core.domain.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PropertyPersistenceAdapter implements FindPropertyPort {
    private final PropertyRepository propertyRepository;

    @Override
    public Optional<Property> findById(Long propertyId, Long ownerId) {
        return propertyRepository.findByIdAndOwnerId(propertyId, ownerId);
    }
}
