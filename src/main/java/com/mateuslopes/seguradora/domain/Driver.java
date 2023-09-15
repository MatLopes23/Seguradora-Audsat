package com.mateuslopes.seguradora.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
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

}