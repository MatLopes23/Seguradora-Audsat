package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Car;
import com.mateuslopes.seguradora.exception.CarNotFoundException;
import com.mateuslopes.seguradora.repository.CarRepository;
import com.mateuslopes.seguradora.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarServiceImpl(carRepository);
    }

    @Test
    void testGetById_ExistingCar() {
        Long carId = 1L;
        Car existingCar = new Car();
        existingCar.setId(carId);
        when(carRepository.findById(carId)).thenReturn(Optional.of(existingCar));

        Car result = carService.getById(carId);

        assertNotNull(result);
        assertEquals(carId, result.getId());
    }

    @Test
    void testGetById_NonExistingCar() {
        Long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carService.getById(carId));
    }
}
