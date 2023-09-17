package com.mateuslopes.seguradora.exception;

import java.io.Serial;

public class CustomerNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;
    public CustomerNotFoundException() {
        super("Customer not found.");
    }
}
