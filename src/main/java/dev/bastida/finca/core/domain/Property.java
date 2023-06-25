package dev.bastida.finca.core.domain;

import dev.bastida.finca.auth.domain.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Builder
public class Property {
    @Id
    private Long id;

    @ManyToOne
    private Account owner;

    @Column
    private Instant createdAt;

    @Column
    private short roomAmount;

    @Column
    private Short bedroomAmount;

    @Column
    private Short bathroomAmount;

    @Column
    private Short garageAmount;

    @Column
    private String description;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address address;
}
