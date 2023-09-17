package com.mateuslopes.seguradora.service;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.dto.BudgetDto;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;

public interface InsuranceService {
    Insurance createInsurance(InsuranceDto insuranceDto);

    Insurance getById(Long insuranceId);

    BudgetDto calculateBudgetByInsurance(Long insuranceId);

    Insurance updateById(Long insuranceId, InsuranceDto insuranceDto);

    void deleteById(Long insuranceId);
}
