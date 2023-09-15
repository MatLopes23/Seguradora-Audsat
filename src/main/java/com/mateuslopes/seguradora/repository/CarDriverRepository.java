package com.mateuslopes.seguradora.repository;

import com.mateuslopes.seguradora.domain.CarDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDriverRepository extends JpaRepository<CarDriver, Long> {
}
