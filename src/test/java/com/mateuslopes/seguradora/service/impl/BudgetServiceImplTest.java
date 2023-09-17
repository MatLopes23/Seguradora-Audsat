package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.*;
import com.mateuslopes.seguradora.repository.CarRepository;
import com.mateuslopes.seguradora.repository.CustomerRepository;
import com.mateuslopes.seguradora.repository.InsuranceRepository;
import com.mateuslopes.seguradora.service.BudgetService;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BudgetServiceImplTest {

    private BudgetService budgetService;

    private Car carTest;

    private Driver driverTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        budgetService = new BudgetServiceImpl();
        carTest = Car.builder()
                .fipe(50000.0)
                .claims(Collections.emptySet())
                .build();

        driverTest = Driver.builder()
                .birthdate(LocalDate.now())
                .build();
    }

    @Test
    void testCalculateByInsurance_DriverAgeInRange() {
        Insurance insurance = new Insurance();
        Car car = this.carTest;
        Customer customer = new Customer();
        Driver driver = new Driver();
        LocalDate birthdateDriver = LocalDate.now().minusYears(20);
        driver.setBirthdate(birthdateDriver);
        customer.setDriver(driver);
        insurance.setCar(car);
        insurance.setCustomer(customer);

        BudgetDto result = budgetService.calculateByInsurance(insurance);

        assertNotNull(result);
        assertEquals(8.0, result.getRisk());
    }

    @Test
    void testCalculateByInsurance_DriverAgeOutOfRange() {
        Insurance insurance = new Insurance();
        Car car = this.carTest;
        Customer customer = new Customer();
        Driver driver = new Driver();
        LocalDate birthdateDriver = LocalDate.now().minusYears(30);
        driver.setBirthdate(birthdateDriver);
        customer.setDriver(driver);
        insurance.setCar(car);
        insurance.setCustomer(customer);

        BudgetDto result = budgetService.calculateByInsurance(insurance);

        assertNotNull(result);
        assertEquals(6.0, result.getRisk());
    }

    @Test
    void testCalculateByInsurance_DriverHasClaims() {
        Insurance insurance = new Insurance();
        Car car = carTest;
        Customer customer = new Customer();
        Driver driver = driverTest;

        Claim claim = new Claim();
        Set<Claim> claimSet = Set.of(claim);

        driver.setClaims(claimSet);
        customer.setDriver(driver);
        insurance.setCar(car);
        insurance.setCustomer(customer);

        BudgetDto result = budgetService.calculateByInsurance(insurance);

        assertNotNull(result);
        assertEquals(8.0, result.getRisk());
    }

    @Test
    void testCalculateByInsurance_CarHasClaims() {
        Insurance insurance = new Insurance();

        Claim claim = new Claim();
        Set<Claim> claimSet = Set.of(claim);
        driverTest.setClaims(claimSet);
        Customer customer = Customer.builder()
                .driver(driverTest)
                .build();

        insurance.setCar(carTest);
        insurance.setCustomer(customer);

        BudgetDto result = budgetService.calculateByInsurance(insurance);

        assertNotNull(result);
        assertEquals(8.0, result.getRisk());
    }
}
