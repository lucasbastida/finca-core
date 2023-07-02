package dev.bastida.finca.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Token {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String value;

    @Column
    public boolean revoked;

    @Column
    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    public Account account;

    public boolean isValid() {
        return !this.expired && !this.revoked;
    }
}
