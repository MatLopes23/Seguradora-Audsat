package com.mateuslopes.seguradora.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;
}
