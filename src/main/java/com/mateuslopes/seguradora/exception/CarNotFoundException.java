package com.mateuslopes.seguradora.exception;

import java.io.Serial;

public class CarNotFoundException extends EntityNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;
    public CarNotFoundException() {
        super("Car not found.");
    }
}
