package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.InsuranceService;
import com.mateuslopes.seguradora.service.dto.InsuranceDto;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Override
    public Insurance createInsurance(InsuranceDto insuranceDomain) {
        return null;
    }

    @Override
    public Insurance getById(Long id) {
        return null;
    }

    @Override
    public Insurance updateById(Long insuranceId, InsuranceDto insuranceDomain) {
        return null;
    }

    @Override
    public Boolean deleteById(Long id) {
        return null;
    }
}
