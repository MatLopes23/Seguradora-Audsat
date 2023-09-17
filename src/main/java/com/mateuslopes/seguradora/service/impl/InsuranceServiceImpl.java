package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Car;
import com.mateuslopes.seguradora.domain.Customer;
import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.exception.InsuranceNotFoundException;
import com.mateuslopes.seguradora.repository.InsuranceRepository;
import com.mateuslopes.seguradora.service.BudgetService;
import com.mateuslopes.seguradora.service.CarService;
import com.mateuslopes.seguradora.service.CustomerService;
import com.mateuslopes.seguradora.service.InsuranceService;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final BudgetService budgetService;
    private final CarService carService;
    private final CustomerService customerService;

    private final InsuranceRepository insuranceRepository;

    @Override
    public Insurance createInsurance(InsuranceDto insuranceDto) {
        final Car car = carService.getById(insuranceDto.getCarId());
        final Customer customer = customerService.getById(insuranceDto.getCustomerId());

        Insurance newInsurance = Insurance
                .builder()
                .car(car)
                .customer(customer)
                .isActive(insuranceDto.getIsActive())
                .creationDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        return insuranceRepository.save(newInsurance);
    }

    @Override
    public Insurance getById(Long insuranceId) {
        return insuranceRepository.findById(insuranceId).orElseThrow(InsuranceNotFoundException::new);
    }

    @Override
    public BudgetDto calculateBudgetByInsurance(Long insuranceId) {
        final Insurance insurance = this.getById(insuranceId);

        return budgetService.calculateByInsurance(insurance);
    }

    @Override
    public Insurance updateById(Long insuranceId, InsuranceDto insuranceDto) {
        Insurance insurance = this.getById(insuranceId);
        final Car car = carService.getById(insuranceDto.getCarId());
        final Customer customer = customerService.getById(insuranceDto.getCustomerId());

        insurance.setCar(car);
        insurance.setCustomer(customer);
        insurance.setIsActive(insuranceDto.getIsActive());
        insurance.setUpdatedDate(LocalDateTime.now());

        return insuranceRepository.save(insurance);
    }

    @Override
    public void deleteById(Long insuranceId) {
        final Insurance insurance = this.getById(insuranceId);

        insuranceRepository.deleteById(insurance.getId());
    }
}
