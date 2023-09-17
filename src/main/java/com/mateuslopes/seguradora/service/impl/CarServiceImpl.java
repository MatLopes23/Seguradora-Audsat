package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Car;
import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.exception.CarNotFoundException;
import com.mateuslopes.seguradora.exception.EntityNotFoundException;
import com.mateuslopes.seguradora.repository.CarRepository;
import com.mateuslopes.seguradora.repository.InsuranceRepository;
import com.mateuslopes.seguradora.service.CarService;
import com.mateuslopes.seguradora.service.InsuranceService;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car getById(Long carId) {
        return carRepository.findById(carId).orElseThrow(CarNotFoundException::new);
    }
}
