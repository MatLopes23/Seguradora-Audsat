package com.mateuslopes.seguradora.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateuslopes.seguradora.domain.Car;
import com.mateuslopes.seguradora.domain.Customer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BudgetDto {

    private Long id;

    private Boolean isActive;

    private LocalDateTime creationDate;

    private LocalDateTime updatedDate;

    private Customer customer;

    private Car car;

    private Double budget;

    private Double risk;

    @JsonIgnore
    public void incrementRisk(Double incrementAmount) {
        this.risk += incrementAmount;
        this.updateBudget();
    }

    @JsonIgnore
    private void updateBudget(){
        this.budget = car.getFipe() * (this.risk/100);
    }
}
