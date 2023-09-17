package com.mateuslopes.seguradora.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @OneToMany(mappedBy = "driver", fetch = FetchType.EAGER)
    private Set<Claim> claims = new HashSet<>();

}