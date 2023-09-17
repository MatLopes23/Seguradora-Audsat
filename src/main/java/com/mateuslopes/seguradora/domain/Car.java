package com.mateuslopes.seguradora.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(name = "car_year", nullable = false)
    private String year;

    @Column(name = "fipe_value", nullable = false)
    private Double fipe;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    private Set<Claim> claims = new HashSet<>();
}
