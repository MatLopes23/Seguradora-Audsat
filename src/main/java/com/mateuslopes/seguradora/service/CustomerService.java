package com.mateuslopes.seguradora.service;

import com.mateuslopes.seguradora.domain.Customer;

public interface CustomerService {
    Customer getById(Long customerId);

}
