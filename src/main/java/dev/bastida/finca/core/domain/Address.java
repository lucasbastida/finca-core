package dev.bastida.finca.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
@Builder
public class Address {
    @Id
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String stateProvince;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String addressLine3;

    @Column
    private String postalCode;

    @Column
    private String sortingCode;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column
    private String additionalInfo;
}
