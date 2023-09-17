package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.*;
import com.mateuslopes.seguradora.exception.InsuranceNotFoundException;
import com.mateuslopes.seguradora.repository.InsuranceRepository;
import com.mateuslopes.seguradora.service.*;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class InsuranceServiceImplTest {

    @Mock
    private BudgetService budgetService;

    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    @Mock
    private InsuranceRepository insuranceRepository;

    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        insuranceService = new InsuranceServiceImpl(budgetService, carService, customerService, insuranceRepository);
    }

    @Test
    void testCreateInsurance() {
        InsuranceDto insuranceDto = new InsuranceDto();
        insuranceDto.setCarId(1L);
        insuranceDto.setCustomerId(2L);
        insuranceDto.setIsActive(true);

        Car car = Car.builder()
                .id(1L)
                .build();
        Customer customer = Customer.builder()
                .id(2L)
                .build();

        when(carService.getById(1L)).thenReturn(car);
        when(customerService.getById(2L)).thenReturn(customer);

        Insurance newInsurance = Insurance.builder()
                .car(car)
                .customer(customer)
                .isActive(true)
                .build();

        when(insuranceRepository.save(any())).thenReturn(newInsurance);

        Insurance result = insuranceService.createInsurance(insuranceDto);

        assertNotNull(result);
        assertEquals(car, result.getCar());
        assertEquals(customer, result.getCustomer());
        assertTrue(result.getIsActive());
    }

    @Test
    void testGetById_ExistingInsurance() {
        Long insuranceId = 1L;
        Insurance existingInsurance = new Insurance();
        existingInsurance.setId(insuranceId);
        when(insuranceRepository.findById(insuranceId)).thenReturn(Optional.of(existingInsurance));

        Insurance result = insuranceService.getById(insuranceId);

        assertNotNull(result);
        assertEquals(insuranceId, result.getId());
    }

    @Test
    void testGetById_NonExistingInsurance() {
        Long insuranceId = 1L;
        when(insuranceRepository.findById(insuranceId)).thenReturn(Optional.empty());

        assertThrows(InsuranceNotFoundException.class, () -> insuranceService.getById(insuranceId));
    }

    @Test
    void testCalculateBudgetByInsurance() {
        Long insuranceId = 1L;
        Insurance insurance = new Insurance();
        when(insuranceRepository.findById(insuranceId)).thenReturn(Optional.of(insurance));

        BudgetDto budgetDto = BudgetDto.builder().build();
        when(budgetService.calculateByInsurance(insurance)).thenReturn(budgetDto);

        BudgetDto result = insuranceService.calculateBudgetByInsurance(insuranceId);

        assertNotNull(result);
    }

    @Test
    void testUpdateById() {
        Long insuranceId = 1L;
        Insurance insurance = new Insurance();
        when(insuranceRepository.findById(insuranceId)).thenReturn(Optional.of(insurance));

        InsuranceDto updatedInsuranceDto = new InsuranceDto();
        updatedInsuranceDto.setCarId(2L);
        updatedInsuranceDto.setCustomerId(3L);
        updatedInsuranceDto.setIsActive(false);

        Car updatedCar = new Car();
        Customer updatedCustomer = new Customer();

        when(carService.getById(2L)).thenReturn(updatedCar);
        when(customerService.getById(3L)).thenReturn(updatedCustomer);

        Insurance updatedInsurance = Insurance.builder()
                .car(updatedCar)
                .customer(updatedCustomer)
                .isActive(false)
                .build();
        when(insuranceRepository.save(any())).thenReturn(updatedInsurance);

        Insurance result = insuranceService.updateById(insuranceId, updatedInsuranceDto);

        assertNotNull(result);
        assertEquals(updatedCar, result.getCar());
        assertEquals(updatedCustomer, result.getCustomer());
        assertFalse(result.getIsActive());
    }

    @Test
    void testDeleteById() {
        Long insuranceId = 1L;
        Insurance existingInsurance = new Insurance();
        existingInsurance.setId(insuranceId);
        when(insuranceRepository.findById(insuranceId)).thenReturn(Optional.of(existingInsurance));

        insuranceService.deleteById(insuranceId);

        verify(insuranceRepository).deleteById(insuranceId);
    }

}
