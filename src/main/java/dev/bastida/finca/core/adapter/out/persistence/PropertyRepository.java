package dev.bastida.finca.core.adapter.out.persistence;

import dev.bastida.finca.core.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByIdAndOwnerId(Long id, Long ownerId);
}
