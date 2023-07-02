package dev.bastida.finca.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class TenantToken {
    @Id
    private Long id;
    @Column
    private UUID shortlink;
    @Column
    private OffsetDateTime expireAt;
    @Column
    private Instant createdAt;
    @Column
    private boolean enabled;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Tenant tenant;

    @Builder
    public TenantToken(OffsetDateTime expireAt, Tenant tenant) {
        this.shortlink = UUID.randomUUID();
        this.expireAt = expireAt;
        this.enabled = true;
        this.createdAt = Instant.now();
        this.tenant = tenant;
    }
}
