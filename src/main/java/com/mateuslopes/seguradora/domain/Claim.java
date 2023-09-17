package com.mateuslopes.seguradora.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claim")
public class Claim {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

}
