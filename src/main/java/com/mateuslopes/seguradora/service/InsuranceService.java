package com.mateuslopes.seguradora.service;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;

public interface InsuranceService {
    Insurance createInsurance(InsuranceDto insuranceDto);

    Insurance getById(Long insuranceId);

    Insurance updateById(Long insuranceId, InsuranceDto insuranceDomain);

    Boolean deleteById(Long insuranceId);
}
