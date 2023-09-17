package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.BudgetService;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {

    private static final Long MIN_AGE = 18L;
    private static final Long MAX_AGE = 25L;
    private static final Double INITIAL_RISK = 6.0;
    private static final Double INCREMENT_RISK = 2.0;

    @Override
    public BudgetDto calculateByInsurance(Insurance insurance) {

        BudgetDto budgetDto = BudgetDto
                .builder()
                .id(insurance.getId())
                .isActive(insurance.getIsActive())
                .car(insurance.getCar())
                .customer(insurance.getCustomer())
                .creationDate(insurance.getCreationDate())
                .updatedDate(insurance.getUpdatedDate())
                .risk(INITIAL_RISK)
                .build();

        budgetDto.incrementRisk(checkDriverAge(insurance) ? INCREMENT_RISK : 0);
        budgetDto.incrementRisk(checkDriverClaim(insurance) ? INCREMENT_RISK : 0);
        budgetDto.incrementRisk(checkCarClaim(insurance) ? INCREMENT_RISK : 0);

        return budgetDto;
    }

    private Boolean checkDriverAge(Insurance insurance){
        LocalDate birthdateDriver = insurance.getCustomer().getDriver().getBirthdate();

        int ageDriver = Period.between(birthdateDriver, LocalDate.now()).getYears();

        return ageDriver >= MIN_AGE && ageDriver <= MAX_AGE;
    }

    private Boolean checkDriverClaim(Insurance insurance){
        return !insurance.getCustomer().getDriver().getClaims().isEmpty();
    }

    private Boolean checkCarClaim(Insurance insurance) {
        return !insurance.getCar().getClaims().isEmpty();
    }
}
