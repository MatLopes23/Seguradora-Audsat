package com.mateuslopes.seguradora.service.dto;


import lombok.Data;

@Data
public class InsuranceDto {
    private Long customerId;

    private Long carId;

    private Boolean isActive;
}
