package com.mateuslopes.seguradora.service.impl;

import com.mateuslopes.seguradora.domain.Customer;
import com.mateuslopes.seguradora.exception.CustomerNotFoundException;
import com.mateuslopes.seguradora.repository.CustomerRepository;
import com.mateuslopes.seguradora.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testGetById_ExistingCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));

        Customer result = customerService.getById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.getId());
    }

    @Test
    void testGetById_NonExistingCustomer() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getById(customerId));
    }
}
