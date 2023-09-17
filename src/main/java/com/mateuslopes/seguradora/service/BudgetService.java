package com.mateuslopes.seguradora.service;

import com.mateuslopes.seguradora.domain.Insurance;
import com.mateuslopes.seguradora.service.dto.BudgetDto;

public interface BudgetService {
    BudgetDto calculateByInsurance(Insurance insurance);
}
