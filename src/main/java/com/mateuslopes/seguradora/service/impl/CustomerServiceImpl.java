package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Car;
import com.mateuslopes.seguradora.domain.Customer;
import com.mateuslopes.seguradora.exception.CarNotFoundException;
import com.mateuslopes.seguradora.exception.CustomerNotFoundException;
import com.mateuslopes.seguradora.repository.CarRepository;
import com.mateuslopes.seguradora.repository.CustomerRepository;
import com.mateuslopes.seguradora.service.CarService;
import com.mateuslopes.seguradora.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
