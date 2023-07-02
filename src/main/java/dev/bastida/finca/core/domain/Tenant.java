package dev.bastida.finca.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Tenant {
    @Id
    private Long id;
    @ManyToOne
    private Property property;
    @ManyToOne
    private Account account;
    @Column
    private Instant createdAt;
    @Column
    private OffsetDateTime startAt;
    @Column
    private OffsetDateTime endAt;
    @Column
    private Double price;
    @Column
    private String agreement;

    @Builder
    public Tenant(Property property, Account account, OffsetDateTime startAt, OffsetDateTime endAt, Double price, String agreement) {
        this.property = property;
        this.account = account;
        this.startAt = startAt;
        this.endAt = endAt;
        this.price = price;
        this.agreement = agreement;
        this.createdAt = Instant.now();
    }
}
